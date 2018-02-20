package com.drpicox.dddshop.cashregister;

import java.io.Serializable;

public class CashRegisterCreated implements Serializable {

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
}
