package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.events.Event;

public abstract class CashRegisterEvent extends Event {
    public CashRegisterEvent() {
        super("cashregister");
    }
}
