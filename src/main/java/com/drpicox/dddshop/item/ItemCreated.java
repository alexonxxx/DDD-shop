package com.drpicox.dddshop.item;

import com.drpicox.dddshop.shared.Money;

import java.io.Serializable;

public class ItemCreated implements Serializable {
    private ItemId itemId;
    private Money money;

    public ItemCreated(ItemId itemId, Money money) {
        this.itemId = itemId;
        this.money = money;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public Money getMoney() {
        return money;
    }
}
