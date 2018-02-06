package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemCtrl;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CashRegisterCtrl {

    @Autowired
    private ItemCtrl itemCtrl;

    @Autowired
    private CashRegisterRepository cashRegisterRepository;

    public CashRegisterId createCashRegister() {
        CashRegister cashRegister = new CashRegister();
        save(cashRegister);

        CashRegisterId cashRegisterId = cashRegister.getCashRegisterId();
        return cashRegisterId;
    }

    public void recordItem(CashRegisterId cashRegisterId, ItemId itemId) {
        CashRegister cashRegister = get(cashRegisterId);
        Money price = itemCtrl.getPrice(itemId);

        cashRegister.recordItem(itemId, price);
        save(cashRegister);
    }

    public void endItemRecords(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        cashRegister.endItemRecords();
        save(cashRegister);
    }

    public Money getTotal(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        return cashRegister.getTotal();
    }

    public void recordCashDelivered(CashRegisterId cashRegisterId, Money cashDelivered) {
        CashRegister cashRegister = get(cashRegisterId);
        cashRegister.recordCashDelivered(cashDelivered);
        save(cashRegister);
    }

    public Money getChange(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        return cashRegister.getChange();
    }

    public void endShoppingTransaction(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = get(cashRegisterId);
        cashRegister.endShoppingTransaction();
        save(cashRegister);
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
