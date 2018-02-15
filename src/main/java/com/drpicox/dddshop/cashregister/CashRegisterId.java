package com.drpicox.dddshop.cashregister;

import java.io.Serializable;

public class CashRegisterId implements Serializable {
    private Long id;

    public CashRegisterId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CashRegisterId{" +
                "id=" + id +
                '}';
    }
}
