package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import java.io.Serializable;

public class ItemRecordsEnded implements Serializable {

    private CashRegisterId cashRegisterId;
    private Long currentTicketNumber;


    public ItemRecordsEnded(CashRegisterId cashRegisterId, Long currentTicketNumber) {
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
