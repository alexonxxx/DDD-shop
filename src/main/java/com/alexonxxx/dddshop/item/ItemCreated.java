package com.alexonxxx.dddshop.item;

import com.alexonxxx.dddshop.shared.Money;

import java.io.Serializable;

public class ItemCreated implements Serializable {
    private ItemId itemId;
    private Money money;

    public ItemCreated(ItemId itemId, Money money) {
        this.itemId = itemId;
        this.money = money;
    }
}
