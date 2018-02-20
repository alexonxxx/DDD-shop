package com.drpicox.dddshop.ticket;

import com.drpicox.dddshop.cashregister.CashDeliveredRecorded;
import com.drpicox.dddshop.cashregister.CashRegisterCreated;
import com.drpicox.dddshop.cashregister.CashRegisterId;
import com.drpicox.dddshop.cashregister.ItemRecorded;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(TicketId.class)
public class Ticket {

    @Id
    private CashRegisterId cashRegisterId;
    @Id
    private Long ticketNumber;

    private Money cashDelivered;

    public Ticket(CashRegisterCreated cashRegisterCreated) {
        this.cashRegisterId = cashRegisterCreated.getCashRegisterId();
        this.ticketNumber = cashRegisterCreated.getCurrentTicketNumber();
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TicketLine> lines;

    public void onItemRecorded(ItemRecorded itemRecorded) {
        lines.add(new TicketLine(itemRecorded.getPrice()));
    }

    public Money getTotal() {
        Money total = new Money(0);
        for (TicketLine line: lines) {
            total = total.plus(line.getPrice());
        }
        return total;
    }

    public void onCashDeliveredRecorded(CashDeliveredRecorded cashDeliveredRecorded) {
        this.cashDelivered = cashDeliveredRecorded.getCashDelivered();
    }

    public Money getChange() {
        return cashDelivered.minus(getTotal());
    }

    public TicketId getTicketId() {
        return new TicketId(cashRegisterId, ticketNumber);
    }

    Ticket(){}
}
