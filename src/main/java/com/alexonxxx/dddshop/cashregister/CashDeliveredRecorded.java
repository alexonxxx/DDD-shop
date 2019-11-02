package com.alexonxxx.dddshop.cashregister;

import com.alexonxxx.dddshop.events.Event;
import com.alexonxxx.dddshop.item.ItemId;
import com.alexonxxx.dddshop.shared.Money;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CashDeliveredRecorded")
public class CashDeliveredRecorded extends CashRegisterEvent {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;
    private Money cashDelivered;


    public CashDeliveredRecorded(CashRegisterId cashRegisterId, Long currentTicketNumber, Money cashDelivered) {
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

    CashDeliveredRecorded() {}
}
