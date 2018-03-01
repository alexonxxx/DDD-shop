package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.ticket.TicketId;

import javax.persistence.*;

@Entity
public class CashRegister {

    @Id
    private Long id;
    private Long currentTicketNumber;


    public CashRegister(CashRegisterCreated cashRegisterCreated) {
        this.id = cashRegisterCreated.getCashRegisterId().getId();
        this.currentTicketNumber = cashRegisterCreated.getCurrentTicketNumber();
    }


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

    @Override
    public String toString() {
        return "CashRegister{" +
                "id=" + id +
                ", currentTicketNumber=" + currentTicketNumber +
                '}';
    }
}
