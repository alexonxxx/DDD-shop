package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.events.Event;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

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
}
