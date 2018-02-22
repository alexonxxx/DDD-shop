package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import java.io.Serializable;

public class ItemRecorded implements Serializable {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;
    private ItemId itemId;
    private Money price;


    public ItemRecorded(CashRegisterId cashRegisterId, Long currentTicketNumber, ItemId itemId, Money price) {
        this.cashRegisterId = cashRegisterId;
        this.currentTicketNumber = currentTicketNumber;
        this.itemId = itemId;
        this.price = price;
    }

    public CashRegisterId getCashRegisterId() {
        return cashRegisterId;
    }

    public Long getCurrentTicketNumber() {
        return currentTicketNumber;
    }

    public Money getPrice() {
        return price;
    }
}
