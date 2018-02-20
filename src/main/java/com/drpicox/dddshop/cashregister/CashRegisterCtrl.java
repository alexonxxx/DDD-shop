package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemCtrl;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;
import com.drpicox.dddshop.ticket.TicketCtrl;
import com.drpicox.dddshop.ticket.TicketId;
import com.drpicox.queue.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashRegisterCtrl {

    @Autowired
    private Queue queue;

    @Autowired
    private ItemCtrl itemCtrl;

    @Autowired
    private TicketCtrl ticketCtrl;

    @Autowired
    private CashRegisterRepository cashRegisterRepository;

    public CashRegisterId createCashRegister() {
        CashRegister cashRegister = new CashRegister();
        save(cashRegister);

        cashRegister.startTransaction();
        save(cashRegister);

        CashRegisterId cashRegisterId = cashRegister.getCashRegisterId();
        queue.send(new CashRegisterCreated(cashRegisterId, cashRegister.getCurrentTicketNumber()));

        return cashRegisterId;
    }

    public void recordItem(CashRegisterId cashRegisterId, ItemId itemId) {
        CashRegister cashRegister = get(cashRegisterId);
        Money price = itemCtrl.getPrice(itemId);
        queue.send(new ItemRecorded(cashRegisterId, cashRegister.getCurrentTicketNumber(), itemId, price));
    }

    public void endItemRecords(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        cashRegister.endItemRecords();
        save(cashRegister);
        queue.send(new ItemRecordsEnded(cashRegisterId, cashRegister.getCurrentTicketNumber()));
    }

    public Money getTotal(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        TicketId ticketId = cashRegister.getTicketId();

        return ticketCtrl.getTotal(ticketId);
    }

    public void recordCashDelivered(CashRegisterId cashRegisterId, Money cashDelivered) {
        CashRegister cashRegister = get(cashRegisterId);
        TicketId ticketId = cashRegister.getTicketId();
        queue.send(new CashDeliveredRecorded(cashRegisterId, cashRegister.getCurrentTicketNumber(), cashDelivered));
    }

    public Money getChange(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        TicketId ticketId = cashRegister.getTicketId();

        return ticketCtrl.getChange(ticketId);
    }

    public void endShoppingTransaction(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        Long currentTicketNumber = cashRegister.getCurrentTicketNumber();
        cashRegister.endShoppingTransaction();
        save(cashRegister);
        queue.send(new ShoppingTransactionEnded(cashRegisterId, currentTicketNumber, cashRegister.getCurrentTicketNumber()));
    }

    public boolean isReadyToRecordANewItem(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        return cashRegister.isReadyToRecordANewItem();
    }

    private CashRegister get(CashRegisterId cashRegisterId) {
        return cashRegisterRepository.findOne(cashRegisterId.getId());
    }

    private void save(CashRegister cashRegister) {
        cashRegisterRepository.save(cashRegister);
    }

}
