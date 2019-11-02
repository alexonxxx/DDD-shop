package com.alexonxxx.dddshop.cashregister;

import java.io.Serializable;
import java.util.Objects;

public class CashRegisterId implements Serializable {
    private Long id;

    public CashRegisterId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashRegisterId that = (CashRegisterId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CashRegisterId{" +
                "id=" + id +
                '}';
    }
}
