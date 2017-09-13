package com.jszybisty.marketcheckout.web.dto;


import java.math.BigDecimal;

public class BasketItemDto {
    private String itemName;
    private Long quantity;
    private BigDecimal price;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
