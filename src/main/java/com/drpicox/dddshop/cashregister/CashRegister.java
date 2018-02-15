package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CashRegister {

    @Id
    @GeneratedValue
    private Long id;

    private Ticket ticket = new Ticket();

    public void recordItem(ItemId itemId, Money price) {
        ticket.recordItem(itemId, price);
    }

    public Money getTotal() {
        return ticket.getTotal();
    }

    public void recordCashDelivered(Money cashDelivered) {
        ticket.recordCashDelivered(cashDelivered);
    }

    public Money getChange() {
        return ticket.getChange();
    }

    public CashRegisterId getCashRegisterId() {
        return new CashRegisterId(id);
    }


    public void endItemRecords() { }
    public boolean isReadyToRecordANewItem() {
        return true;
    }
    public void endShoppingTransaction() {
        ticket = new Ticket();
    }

}
