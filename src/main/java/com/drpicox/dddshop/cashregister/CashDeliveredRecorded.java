package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import java.io.Serializable;

public class CashDeliveredRecorded implements Serializable {

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
}
