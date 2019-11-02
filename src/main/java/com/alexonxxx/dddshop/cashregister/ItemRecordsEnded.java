package com.alexonxxx.dddshop.cashregister;

import com.alexonxxx.dddshop.events.Event;
import com.alexonxxx.dddshop.item.ItemId;
import com.alexonxxx.dddshop.shared.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "ItemRecordsEnded")
public class ItemRecordsEnded extends CashRegisterEvent {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;


    public ItemRecordsEnded(CashRegisterId cashRegisterId, Long currentTicketNumber) {
        this.cashRegisterId = cashRegisterId;
        this.currentTicketNumber = currentTicketNumber;
    }

    public CashRegisterId getCashRegisterId() {
        return cashRegisterId;
    }

    ItemRecordsEnded(){}
}
