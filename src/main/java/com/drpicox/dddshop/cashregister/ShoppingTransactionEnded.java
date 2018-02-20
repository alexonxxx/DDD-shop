package com.drpicox.dddshop.cashregister;

import java.io.Serializable;

public class ShoppingTransactionEnded implements Serializable {

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

    public Long getCurrentTicketNumber() {
        return currentTicketNumber;
    }

    public Long getNextTicketNumber() {
        return nextTicketNumber;
    }
}
