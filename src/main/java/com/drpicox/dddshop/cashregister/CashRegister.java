package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CashRegister {

    @Id
    @GeneratedValue
    private Long id;

    private Money total;
    private Money cashDelivered;

    public void recordItem(ItemId itemId, Money price) {
        total = price;
    }

    public void endItemRecords() {

    }

    public Money getTotal() {
        return total;
    }

    public void recordCashDelivered(Money cashDelivered) {
        this.cashDelivered = cashDelivered;
    }

    public Money getChange() {
        return cashDelivered.minus(total);
    }

    public void endShoppingTransaction() {
    }

    public boolean isReadyToRecordANewItem() {
        return true;
    }


    public CashRegisterId getCashRegisterId() {
        return new CashRegisterId(id);
    }
}
