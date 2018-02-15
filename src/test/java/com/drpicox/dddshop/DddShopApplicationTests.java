package com.drpicox.dddshop;

import com.drpicox.dddshop.cashregister.CashRegisterCtrl;
import com.drpicox.dddshop.cashregister.CashRegisterId;
import com.drpicox.dddshop.shared.Money;
import com.drpicox.dddshop.item.ItemCtrl;
import com.drpicox.dddshop.item.ItemId;
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
     Ticket: document generated with one transaction details
    */
	@Test
    public void sellOneItem() {
        ItemId itemId = getItemWithPriceInMoney();
        Money price = getItemPrice(itemId);
        CashRegisterId cashRegisterId = getCashRegister();

        recordItemAtCashRegister(itemId, cashRegisterId);
        endCashRegisterItemRecords(cashRegisterId);
        Money total = getCashRegisterTotal(cashRegisterId);
        Money cashDelivered = getCashDeliveredByCustomer();
        recordCashDeliveredAtCashRegister(cashDelivered, cashRegisterId);
        Money change = getCashRegisterChange(cashRegisterId);
        endShoppingTransaction(cashRegisterId);

        assertEquals(price, total);
        assertEquals(new Money(3), change);
        assertEquals(true, isCashRegisterReadyToRecordANewItem(cashRegisterId));
    }

    private boolean isCashRegisterReadyToRecordANewItem(CashRegisterId cashRegisterId) {
        return cashRegisterCtrl.isReadyToRecordANewItem(cashRegisterId);
    }

    private void endShoppingTransaction(CashRegisterId cashRegisterId) {
        cashRegisterCtrl.endShoppingTransaction(cashRegisterId);
    }

    private Money getCashRegisterChange(CashRegisterId cashRegisterId) {
        return cashRegisterCtrl.getChange(cashRegisterId);
    }

    private void recordCashDeliveredAtCashRegister(Money cashDelivered, CashRegisterId cashRegisterId) {
        cashRegisterCtrl.recordCashDelivered(cashRegisterId, cashDelivered);
    }

    private Money getCashRegisterTotal(CashRegisterId cashRegisterId) {
        return cashRegisterCtrl.getTotal(cashRegisterId);
    }

    private void endCashRegisterItemRecords(CashRegisterId cashRegisterId) {
        cashRegisterCtrl.endItemRecords(cashRegisterId);
    }

    private Money getItemPrice(ItemId itemId) {
        return itemCtrl.getPrice(itemId);
    }

    private void recordItemAtCashRegister(ItemId itemId, CashRegisterId cashRegisterId) {
        cashRegisterCtrl.recordItem(cashRegisterId, itemId);
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
