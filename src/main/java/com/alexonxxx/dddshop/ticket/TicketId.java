package com.alexonxxx.dddshop.ticket;

import com.alexonxxx.dddshop.cashregister.CashRegisterId;

import java.io.Serializable;
import java.util.Objects;

public class TicketId implements Serializable {

    private CashRegisterId cashRegisterId;
    private Long ticketNumber;

    public TicketId(CashRegisterId cashRegisterId, Long ticketNumber) {
        this.cashRegisterId = cashRegisterId;
        this.ticketNumber = ticketNumber;
    }

    TicketId(){}
}
