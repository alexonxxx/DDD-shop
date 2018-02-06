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

    private Map<CashRegisterId,CashRegister> cashRegisters = new HashMap<>();

    public CashRegisterId createCashRegister() {
        CashRegister cashRegister = new CashRegister();
        cashRegisterRepository.save(cashRegister);

        CashRegisterId cashRegisterId = cashRegister.getCashRegisterId();
        cashRegisters.put(cashRegisterId, cashRegister);
        return cashRegisterId;
    }

    public void recordItem(CashRegisterId cashRegisterId, ItemId itemId) {
        CashRegister cashRegister = cashRegisterRepository.findOne(cashRegisterId.getId());
        Money price = itemCtrl.getPrice(itemId);

        cashRegister.recordItem(itemId, price);
        cashRegisterRepository.save(cashRegister);
    }

    public void endItemRecords(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisterRepository.findOne(cashRegisterId.getId());
        cashRegister.endItemRecords();
        cashRegisterRepository.save(cashRegister);
    }

    public Money getTotal(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisterRepository.findOne(cashRegisterId.getId());
        return cashRegister.getTotal();
    }

    public void recordCashDelivered(CashRegisterId cashRegisterId, Money cashDelivered) {
        CashRegister cashRegister = cashRegisterRepository.findOne(cashRegisterId.getId());
        cashRegister.recordCashDelivered(cashDelivered);
        cashRegisterRepository.save(cashRegister);
    }

    public Money getChange(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisterRepository.findOne(cashRegisterId.getId());
        return cashRegister.getChange();
    }

    public void endShoppingTransaction(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisterRepository.findOne(cashRegisterId.getId());
        cashRegister.endShoppingTransaction();
        cashRegisterRepository.save(cashRegister);
    }

    public boolean isReadyToRecordANewItem(CashRegisterId cashRegisterId) {
        CashRegister cashRegister = cashRegisterRepository.findOne(cashRegisterId.getId());
        return cashRegister.isReadyToRecordANewItem();
    }
}
