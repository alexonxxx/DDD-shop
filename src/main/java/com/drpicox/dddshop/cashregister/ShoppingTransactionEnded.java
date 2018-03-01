package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.events.Event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "ShoppingTransactionEnded")
public class ShoppingTransactionEnded extends Event {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;
    private Long nextTicketNumber;


    public ShoppingTransactionEnded(CashRegisterId cashRegisterId, Long currentTicketNumber, Long nextTicketNumber) {
        super("cashregister");
        this.cashRegisterId = cashRegisterId;
        this.currentTicketNumber = currentTicketNumber;
        this.nextTicketNumber = nextTicketNumber;
    }

}
