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

    private Map<CashRegisterId,CashRegister> cashRegisters = new HashMap<>();

    public CashRegisterId createCashRegister() {
        CashRegisterId cashRegisterId = new CashRegisterId();
        CashRegister cashRegister = new CashRegister();

        cashRegisters.put(cashRegisterId, cashRegister);
        return cashRegisterId;
    }

    public void recordItem(CashRegisterId cashRegisterId, ItemId itemId) {
        CashRegister cashRegister = cashRegisters.get(cashRegisterId);
        Money price = itemCtrl.getPrice(itemId);

        cashRegister.recordItem(itemId, price);
    }

    public void endItemRecords(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisters.get(cashRegisterId);
        cashRegister.endItemRecords();
    }

    public Money getTotal(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisters.get(cashRegisterId);
        return cashRegister.getTotal();
    }

    public void recordCashDelivered(CashRegisterId cashRegisterId, Money cashDelivered) {
        CashRegister cashRegister = cashRegisters.get(cashRegisterId);
        cashRegister.recordCashDelivered(cashDelivered);
    }

    public Money getChange(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisters.get(cashRegisterId);
        return cashRegister.getChange();
    }

    public void endShoppingTransaction(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisters.get(cashRegisterId);
        cashRegister.endShoppingTransaction();
    }

    public boolean isReadyToRecordANewItem(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisters.get(cashRegisterId);
        return cashRegister.isReadyToRecordANewItem();
    }
}
