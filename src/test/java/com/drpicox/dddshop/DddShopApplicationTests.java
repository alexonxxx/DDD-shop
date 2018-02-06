package com.drpicox.dddshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DddShopApplicationTests {

    @Autowired
    private ItemCtrl itemCtrl;

    @Autowired
    private CashRegisterCtrl cashRegisterCtrl;

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
        ItemId itemId = getItemWithPriceInMoney();
        Money price = itemCtrl.getPrice(itemId);
        CashRegisterId cashRegisterId = getCashRegister();

        cashRegisterCtrl.recordItem(cashRegisterId, itemId);
        cashRegisterCtrl.endItemRecords(cashRegisterId);
        Money total = cashRegisterCtrl.getTotal(cashRegisterId);
        Money cashDelivered = getCashDeliveredByCustomer();
        cashRegisterCtrl.recordCashDelivered(cashRegisterId, cashDelivered);
        Money change = cashRegisterCtrl.getChange(cashRegisterId);
        cashRegisterCtrl.endShoppingTransaction(cashRegisterId);

        assertEquals(price, total);
        assertEquals(new Money(3), change);
        assertEquals(true, cashRegisterCtrl.isReadyToRecordANewItem(cashRegisterId));
    }

    public ItemId getItemWithPriceInMoney() {
	    return itemCtrl.createItem(new Money(17));
    }

    public CashRegisterId getCashRegister() {
        return cashRegisterCtrl.createCashRegister();
    }

    public Money getCashDeliveredByCustomer() {
        return new Money(20);
    }
}
