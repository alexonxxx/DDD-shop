package com.alexonxxx.dddshop.cashregister;

import com.alexonxxx.dddshop.events.Event;

public abstract class CashRegisterEvent extends Event {
    public CashRegisterEvent() {
        super("cashregister");
    }
}
