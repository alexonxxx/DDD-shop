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

}
