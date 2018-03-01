package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.events.Event;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CashDeliveredRecorded")
public class CashDeliveredRecorded extends Event {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;
    private Money cashDelivered;


    public CashDeliveredRecorded(CashRegisterId cashRegisterId, Long currentTicketNumber, Money cashDelivered) {
        super("cashregister");
        this.cashRegisterId = cashRegisterId;
        this.currentTicketNumber = currentTicketNumber;
        this.cashDelivered = cashDelivered;
    }

    public CashRegisterId getCashRegisterId() {
        return cashRegisterId;
    }

    public Long getCurrentTicketNumber() {
        return currentTicketNumber;
    }

    public Money getCashDelivered() {
        return cashDelivered;
    }
}
