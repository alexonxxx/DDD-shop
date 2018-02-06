package com.drpicox.dddshop;

public class CashRegister {

    private Money total;
    private Money cashDelivered;

    public void recordItem(ItemId itemId, Money price) {
        total = price;
    }

    public void endItemRecords() {

    }

    public Money getTotal() {
        return total;
    }

    public void recordCashDelivered(Money cashDelivered) {
        this.cashDelivered = cashDelivered;
    }

    public Money getChange() {
        return cashDelivered.minus(total);
    }

    public void endShoppingTransaction() {
    }

    public boolean isReadyToRecordANewItem() {
        return true;
    }
}
