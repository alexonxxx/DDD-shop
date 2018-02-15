package com.drpicox.dddshop.shared;

import java.io.Serializable;
import java.util.Objects;

public class Money implements Serializable {
    private int quantity;

    public Money(int quantity) {
        this.quantity = quantity;
    }

    public Money minus(Money other) {
        return new Money(this.quantity - other.quantity);
    }
    public Money plus(Money other) {
        return new Money(this.quantity + other.quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return quantity == money.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }
}
