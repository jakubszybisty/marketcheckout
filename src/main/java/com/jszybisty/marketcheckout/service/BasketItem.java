package com.jszybisty.marketcheckout.service;

import com.jszybisty.marketcheckout.model.Item;

import java.math.BigDecimal;

/**
 * Created by jakub on 30.08.2017.
 */
public class BasketItem {
    private Item item;
    private Long quantity;
    private BigDecimal price;

    public BasketItem(Item item, Long quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
