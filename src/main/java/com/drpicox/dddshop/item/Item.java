package com.drpicox.dddshop.item;

import com.drpicox.dddshop.Money;

public class Item {
    private Money price;

    public Item(Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }
}
