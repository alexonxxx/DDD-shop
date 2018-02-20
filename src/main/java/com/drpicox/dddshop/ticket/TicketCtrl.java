package com.drpicox.dddshop.ticket;

import com.drpicox.dddshop.cashregister.CashDeliveredRecorded;
import com.drpicox.dddshop.cashregister.CashRegisterCreated;
import com.drpicox.dddshop.cashregister.CashRegisterId;
import com.drpicox.dddshop.cashregister.ItemRecorded;
import com.drpicox.dddshop.item.ItemId;
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
            this.createTicket(cashRegisterCreated.getCashRegisterId(), cashRegisterCreated.getCurrentTicketNumber());
        });
        queue.receive(ItemRecorded.class, (itemRecorded) -> {
            this.recordItem(itemRecorded.getCashRegisterId(), itemRecorded.getCurrentTicketNumber(), itemRecorded.getItemId(), itemRecorded.getPrice());
        });
        queue.receive(CashDeliveredRecorded.class, (cashDeliveredRecorded) -> {
            this.recordCashDelivered(cashDeliveredRecorded.getCashRegisterId(), cashDeliveredRecorded.getCurrentTicketNumber(), cashDeliveredRecorded.getCashDelivered());
        });
    }

    public TicketId createTicket(CashRegisterId cashRegisterId, Long ticketNumber) {
        Ticket ticket = new Ticket(cashRegisterId, ticketNumber);
        save(ticket);


        TicketId ticketId = ticket.getTicketId();
        return ticketId;
    }

    public void recordItem(CashRegisterId cashRegisterId, Long ticketNumber, ItemId itemId, Money price) {
        Ticket ticket = get(new TicketId(cashRegisterId, ticketNumber));

        ticket.recordItem(itemId, price);
        save(ticket);
    }

    public Money getTotal(TicketId ticketId) {
        Ticket ticket = get(ticketId);
        return ticket.getTotal();
    }

    public void recordCashDelivered(CashRegisterId cashRegisterId, Long ticketNumber, Money cashDelivered) {
        Ticket ticket = get(new TicketId(cashRegisterId, ticketNumber));
        ticket.recordCashDelivered(cashDelivered);
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
