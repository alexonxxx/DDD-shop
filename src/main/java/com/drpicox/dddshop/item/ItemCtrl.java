package com.drpicox.dddshop.item;

import com.drpicox.dddshop.shared.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ItemCtrl {

    @Autowired
    private ItemRepository itemRepository;

    private Map<ItemId, Item> items = new HashMap<>();

    public ItemId createItem(Money money) {
        Item item = new Item(money);
        save(item);

        ItemId itemId = item.getItemId();
        return itemId;
    }

    public Money getPrice(ItemId itemId) {
        Item item = get(itemId);
        return item.getPrice();
    }

    private Item get(ItemId itemId) {
        return itemRepository.findOne(itemId.getId());
    }

    private void save(Item item) {
        itemRepository.save(item);
    }
}
