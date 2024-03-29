package com.alexonxxx.dddshop.cashregister;

import com.alexonxxx.dddshop.events.Event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "ShoppingTransactionEnded")
public class ShoppingTransactionEnded extends CashRegisterEvent {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;
    private Long nextTicketNumber;


    public ShoppingTransactionEnded(CashRegisterId cashRegisterId, Long currentTicketNumber, Long nextTicketNumber) {
        this.cashRegisterId = cashRegisterId;
        this.currentTicketNumber = currentTicketNumber;
        this.nextTicketNumber = nextTicketNumber;
    }

    public CashRegisterId getCashRegisterId() {
        return cashRegisterId;
    }

    ShoppingTransactionEnded(){}
}
