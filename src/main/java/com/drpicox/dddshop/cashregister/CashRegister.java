package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.ticket.TicketId;

import javax.persistence.*;

@Entity
public class CashRegister {

    @Id
    @GeneratedValue
    private Long id;

    public CashRegister(CashRegisterCreated cashRegisterCreated) {
        this.id = cashRegisterCreated.getCashRegisterId().getId();
    }

    private Long currentTicketNumber = 0L;


    public void startTransaction() {
        currentTicketNumber++;
    }

    public TicketId getTicketId() {
        return new TicketId(getCashRegisterId(), currentTicketNumber);
    }

    public CashRegisterId getCashRegisterId() {
        return new CashRegisterId(id);
    }

    public void endItemRecords() { }
    public boolean isReadyToRecordANewItem() {
        return true;
    }
    public void endShoppingTransaction() {
        startTransaction();
    }

    public Long getCurrentTicketNumber() {
        return currentTicketNumber;
    }

    CashRegister() {}
}
