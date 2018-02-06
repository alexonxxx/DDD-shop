package com.drpicox.dddshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DddShopApplicationTests {

	@Test
	public void contextLoads() {
	}

    /* Language:

     Item: a physical product to sell
     Money: quantifiable amount of value to exchange
     Price: amount of money associated to an item
     CashRegister: machine that records shopping transactions
     RecordItem: annotate in the current transaction of a CashRegister an item
     EndRecords: end of a list of items in a shopping transaction
     ShoppingTransaction: a set of items sold in group
     Total: the total sum of prices of all items of the same ShoppingTransaction
     CashDelivered: an amount of money delivered to satisfy a shopping transaction equals or greater than value
     Change: an amount of money to return one total is satisfied with cashDelivered
     EndShoppingTransaction: finishes the current transactions and makes the CashRegister ready for a new one
    */
	@Test
    public void sellOneItem() {
        Item item = getItemWithPriceInMoney();
        Money price = item.getPrice();
        CashRegister cashRegister = getCashRegister();

        cashRegister.recordItem(item);
        cashRegister.endItemRecords();
        Money total = cashRegister.getTotal();
        Money cashDelivered = getCashDeliveredByCustomer();
        cashRegister.recordCashDelivered(cashDelivered);
        Money change = cashRegister.getChange();
        cashRegister.endShoppingTransaction();

        assertEquals(price, total);
        assertEquals(new Money(3), change);
        assertEquals(true, cashRegister.isReadyToRecordANewItem());
    }

    public Item getItemWithPriceInMoney() {
        return new Item(new Money(17));
    }

    public CashRegister getCashRegister() {
        return new CashRegister();
    }

    public Money getCashDeliveredByCustomer() {
        return new Money(20);
    }
}
