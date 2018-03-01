package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.events.Event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CashRegisterCreated")
public class CashRegisterCreated extends Event {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;

    public CashRegisterCreated(CashRegisterId cashRegisterId, Long currentTicketNumber) {
        super("cashregister");
        this.cashRegisterId = cashRegisterId;
        this.currentTicketNumber = currentTicketNumber;
    }

    public CashRegisterId getCashRegisterId() {
        return cashRegisterId;
    }

    public Long getCurrentTicketNumber() {
        return currentTicketNumber;
    }
}
