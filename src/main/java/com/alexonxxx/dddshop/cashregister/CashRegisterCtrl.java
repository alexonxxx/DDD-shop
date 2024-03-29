package com.alexonxxx.dddshop.cashregister;

import com.alexonxxx.dddshop.events.Event;
import com.alexonxxx.dddshop.events.EventRepository;
import com.alexonxxx.dddshop.item.ItemCtrl;
import com.alexonxxx.dddshop.item.ItemId;
import com.alexonxxx.dddshop.shared.Money;
import com.alexonxxx.dddshop.ticket.TicketCtrl;
import com.alexonxxx.dddshop.ticket.TicketId;
import com.alexonxxx.queue.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CashRegisterCtrl {

    @Autowired
    private Queue queue;

    @Autowired
    private ItemCtrl itemCtrl;

    @Autowired
    private TicketCtrl ticketCtrl;

    @Autowired
    private EventRepository eventRepository;

    public CashRegisterId createCashRegister() {
        CashRegisterCreated cashRegisterCreated = getDictionary().createCashRegister();

        saveAndSend(cashRegisterCreated);

        return cashRegisterCreated.getCashRegisterId();
    }

    public void recordItem(CashRegisterId cashRegisterId, ItemId itemId) {
        Money price = itemCtrl.getPrice(itemId);
        ItemRecorded itemRecorded = getDictionary().recordItem(cashRegisterId, itemId, price);

        saveAndSend(itemRecorded);
    }

    public void endItemRecords(CashRegisterId cashRegisterId) {
        ItemRecordsEnded itemRecordsEnded = getDictionary().endItemRecords(cashRegisterId);

        saveAndSend(itemRecordsEnded);
    }

    public Money getTotal(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        TicketId ticketId = cashRegister.getTicketId();

        return ticketCtrl.getTotal(ticketId);
    }

    public void recordCashDelivered(CashRegisterId cashRegisterId, Money cashDelivered) {
        CashDeliveredRecorded cashDeliveredRecorded = getDictionary().recordCashDelivered(cashRegisterId, cashDelivered);

        saveAndSend(cashDeliveredRecorded);
    }

    public Money getChange(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        TicketId ticketId = cashRegister.getTicketId();

        return ticketCtrl.getChange(ticketId);
    }

    public void endShoppingTransaction(CashRegisterId cashRegisterId) {
        ShoppingTransactionEnded shoppingTransactionEnded = getDictionary().endShoppingTransaction(cashRegisterId);

        saveAndSend(shoppingTransactionEnded);
    }

    public boolean isReadyToRecordANewItem(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        return cashRegister.isReadyToRecordANewItem();
    }

    private CashRegister get(CashRegisterId cashRegisterId) {
        return getDictionary().get(cashRegisterId);
    }

    private CashRegisterDictionary getDictionary() {
        List<Event> events = eventRepository.findByAggregateOrderBySequenceNumberAsc("cashregister");

        CashRegisterDictionary dictionary = new CashRegisterDictionary();
        dictionary.apply(events);

        return dictionary;
    }

    private void saveAndSend(Event event) {
        eventRepository.save(event);
        queue.send(event);
    }

}
