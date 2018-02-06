package com.drpicox.dddshop;

public class Item {
    private Money price;

    public Item(Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }
}
