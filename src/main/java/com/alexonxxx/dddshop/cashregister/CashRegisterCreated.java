package com.alexonxxx.dddshop.cashregister;

import com.alexonxxx.dddshop.events.Event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CashRegisterCreated")
public class CashRegisterCreated extends CashRegisterEvent {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;

    public CashRegisterCreated(CashRegisterId cashRegisterId, Long currentTicketNumber) {
        this.cashRegisterId = cashRegisterId;
        this.currentTicketNumber = currentTicketNumber;
    }

    public CashRegisterId getCashRegisterId() {
        return cashRegisterId;
    }

    public Long getCurrentTicketNumber() {
        return currentTicketNumber;
    }

    CashRegisterCreated(){}
}
