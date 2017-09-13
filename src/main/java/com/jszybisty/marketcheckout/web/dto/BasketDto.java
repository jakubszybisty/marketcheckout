package com.jszybisty.marketcheckout.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketDto {
    private List<BasketItemDto> basketItemDtos = new ArrayList<>();
    private BigDecimal specialPromotionDiscount;
    private BigDecimal priceWithSpecialPromotion;
    private BigDecimal priceWithoutSpecialPromotion;

    public List<BasketItemDto> getBasketItemDtos() {
        return basketItemDtos;
    }

    public void setBasketItemDtos(List<BasketItemDto> basketItemDtos) {
        this.basketItemDtos = basketItemDtos;
    }

    public BigDecimal getSpecialPromotionDiscount() {
        return specialPromotionDiscount;
    }

    public void setSpecialPromotionDiscount(BigDecimal specialPromotionDiscount) {
        this.specialPromotionDiscount = specialPromotionDiscount;
    }

    public BigDecimal getPriceWithSpecialPromotion() {
        return priceWithSpecialPromotion;
    }

    public void setPriceWithSpecialPromotion(BigDecimal priceWithSpecialPromotion) {
        this.priceWithSpecialPromotion = priceWithSpecialPromotion;
    }

    public BigDecimal getPriceWithoutSpecialPromotion() {
        return priceWithoutSpecialPromotion;
    }

    public void setPriceWithoutSpecialPromotion(BigDecimal priceWithoutSpecialPromotion) {
        this.priceWithoutSpecialPromotion = priceWithoutSpecialPromotion;
    }
}
