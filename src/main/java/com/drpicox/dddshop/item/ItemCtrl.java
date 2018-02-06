package com.drpicox.dddshop.item;

import com.drpicox.dddshop.Money;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ItemCtrl {

    private Map<ItemId, Item> items = new HashMap<>();

    public ItemId createItem(Money money) {
        ItemId itemId = new ItemId();
        Item item = new Item(money);

        items.put(itemId, item);
        return itemId;
    }

    public Money getPrice(ItemId itemId) {
        Item item = items.get(itemId);
        return item.getPrice();
    }
}
