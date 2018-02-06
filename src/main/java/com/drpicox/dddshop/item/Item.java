package com.drpicox.dddshop.item;

import com.drpicox.dddshop.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private Money price;

    public Item(Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }

    public ItemId getItemId() {
        return new ItemId(id);
    }

    Item(){}
}
