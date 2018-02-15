package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import java.io.Serializable;

public class Ticket implements Serializable{
    private Money total;
    private Money cashDelivered;

    public void recordItem(ItemId itemId, Money price) {
        total = price;
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

}
