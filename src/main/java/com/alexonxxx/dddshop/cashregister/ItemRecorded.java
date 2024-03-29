package com.alexonxxx.dddshop.cashregister;

import com.alexonxxx.dddshop.events.Event;
import com.alexonxxx.dddshop.item.ItemId;
import com.alexonxxx.dddshop.shared.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "ItemRecorded")
public class ItemRecorded extends CashRegisterEvent {

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

    ItemRecorded(){}

}
