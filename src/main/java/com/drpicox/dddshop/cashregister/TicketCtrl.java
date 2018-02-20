package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemCtrl;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketCtrl {

    @Autowired
    private TicketRepository ticketRepository;

    public TicketId createTicket(CashRegisterId cashRegisterId, Long ticketNumber) {
        Ticket ticket = new Ticket(cashRegisterId, ticketNumber);
        save(ticket);


        TicketId ticketId = ticket.getTicketId();
        return ticketId;
    }

    public void recordItem(TicketId ticketId, ItemId itemId, Money price) {
        Ticket ticket = get(ticketId);

        ticket.recordItem(itemId, price);
        save(ticket);
    }

    public Money getTotal(TicketId ticketId) {
        Ticket ticket = get(ticketId);
        return ticket.getTotal();
    }

    public void recordCashDelivered(TicketId ticketId, Money cashDelivered) {
        Ticket ticket = get(ticketId);
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
