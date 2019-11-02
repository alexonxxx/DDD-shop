package com.alexonxxx.dddshop;

import com.alexonxxx.dddshop.cashregister.CashRegisterCtrl;
import com.alexonxxx.dddshop.cashregister.CashRegisterId;
import com.alexonxxx.dddshop.shared.Money;
import com.alexonxxx.dddshop.item.ItemCtrl;
import com.alexonxxx.dddshop.item.ItemId;
import com.alexonxxx.queue.Queue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DddShopApplicationTests {

    @Autowired
    private Queue queue;

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
     TicketLine: each item registered in a ticket with its quantity, price when registered, etc...
    */
	@Test
    public void sellOneItem() {
	    // GIVE
        ItemId itemId = getItemWithPriceInMoney();
        Money price = getItemPrice(itemId);
        CashRegisterId cashRegisterId = getCashRegister();

        // WHEN
        recordItemAtCashRegister(itemId, cashRegisterId);
        endCashRegisterItemRecords(cashRegisterId);
        Money total = getCashRegisterTotal(cashRegisterId);
        Money cashDelivered = getCashDeliveredByCustomer();
        recordCashDeliveredAtCashRegister(cashDelivered, cashRegisterId);
        Money change = getCashRegisterChange(cashRegisterId);
        endShoppingTransaction(cashRegisterId);

        // THEN
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
        queue.deliverMessages();
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
        queue.deliverMessages();
    }

    public ItemId getItemWithPriceInMoney() {
	    ItemId itemId = itemCtrl.createItem(new Money(17));
	    queue.deliverMessages();
	    return itemId;
    }

    public CashRegisterId getCashRegister() {
        CashRegisterId cashRegisterId = cashRegisterCtrl.createCashRegister();
        queue.deliverMessages();
        return cashRegisterId;
    }

    public Money getCashDeliveredByCustomer() {
        return new Money(20);
    }
}
