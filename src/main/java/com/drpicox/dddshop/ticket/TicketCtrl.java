package com.drpicox.dddshop.ticket;

import com.drpicox.dddshop.cashregister.CashDeliveredRecorded;
import com.drpicox.dddshop.cashregister.CashRegisterCreated;
import com.drpicox.dddshop.cashregister.ItemRecorded;
import com.drpicox.dddshop.shared.Money;
import com.drpicox.queue.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketCtrl {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private void receiveMessages(Queue queue) {
        queue.receive(CashRegisterCreated.class, (cashRegisterCreated) -> {
            this.onCashRegisterCreated(cashRegisterCreated);
        });
        queue.receive(ItemRecorded.class, (itemRecorded) -> {
            this.onItemRecorded(itemRecorded);
        });
        queue.receive(CashDeliveredRecorded.class, (cashDeliveredRecorded) -> {
            this.onCashDeliveredRecorded(cashDeliveredRecorded);
        });
    }

    public TicketId onCashRegisterCreated(CashRegisterCreated cashRegisterCreated) {
        Ticket ticket = new Ticket(cashRegisterCreated);
        save(ticket);


        TicketId ticketId = ticket.getTicketId();
        return ticketId;
    }

    public void onItemRecorded(ItemRecorded itemRecorded) {
        Ticket ticket = get(new TicketId(
                itemRecorded.getCashRegisterId(),
                itemRecorded.getCurrentTicketNumber()
        ));

        ticket.onItemRecorded(itemRecorded);
        save(ticket);
    }

    public Money getTotal(TicketId ticketId) {
        Ticket ticket = get(ticketId);
        return ticket.getTotal();
    }

    public void onCashDeliveredRecorded(CashDeliveredRecorded cashDeliveredRecorded) {
        Ticket ticket = get(new TicketId(
                cashDeliveredRecorded.getCashRegisterId(),
                cashDeliveredRecorded.getCurrentTicketNumber()
        ));
        ticket.onCashDeliveredRecorded(cashDeliveredRecorded);
        save(ticket);
    }

    public Money getChange(TicketId ticketId) {
        Ticket ticket = get(ticketId);
        return ticket.getChange();
    }

    private Ticket get(TicketId ticketId) {
        return ticketRepository.findOne(ticketId);
    }

    private void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
