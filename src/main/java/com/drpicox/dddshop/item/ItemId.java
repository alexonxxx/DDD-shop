package com.drpicox.dddshop.item;

import java.io.Serializable;

public class ItemId implements Serializable {

    private Long id;

    public ItemId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
