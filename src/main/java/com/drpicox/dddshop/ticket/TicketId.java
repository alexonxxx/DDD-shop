package com.drpicox.dddshop.ticket;

import com.drpicox.dddshop.cashregister.CashRegisterId;

import java.io.Serializable;
import java.util.Objects;

public class TicketId implements Serializable {

    private CashRegisterId cashRegisterId;
    private Long ticketNumber;

    public TicketId(CashRegisterId cashRegisterId, Long ticketNumber) {
        this.cashRegisterId = cashRegisterId;
        this.ticketNumber = ticketNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketId ticketId = (TicketId) o;
        return Objects.equals(cashRegisterId, ticketId.cashRegisterId) &&
                Objects.equals(ticketNumber, ticketId.ticketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cashRegisterId, ticketNumber);
    }

    TicketId(){}
}
