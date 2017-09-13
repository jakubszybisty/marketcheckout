package com.jszybisty.marketcheckout.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by jakub on 05.09.2017.
 */
public class Basket {
    private List<BasketItem> basketItems = new ArrayList<>();
    private BigDecimal specialPromotionDiscount;
    private BigDecimal priceWithSpecialPromotion;
    private BigDecimal priceWithoutSpecialPromotion;

    private Basket(BasketBuilder basketBuilder) {
        this.basketItems = basketBuilder.basketItems;
        this.specialPromotionDiscount = basketBuilder.specialPromotionDiscount;
        this.priceWithSpecialPromotion = basketBuilder.priceWithSpecialPromotion;
        this.priceWithoutSpecialPromotion = basketBuilder.priceWithoutSpecialPromotion;
    }

    public static BasketBuilder of(List<BasketItem> basketItems) {
        return new BasketBuilder(basketItems);
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public BigDecimal getSpecialPromotionDiscount() {
        return specialPromotionDiscount;
    }

    public BigDecimal getPriceWithSpecialPromotion() {
        return priceWithSpecialPromotion;
    }

    public BigDecimal getPriceWithoutSpecialPromotion() {
        return priceWithoutSpecialPromotion;
    }

    public static class BasketBuilder {
        private List<BasketItem> basketItems = new ArrayList<>();
        private BigDecimal specialPromotionDiscount;
        private BigDecimal priceWithSpecialPromotion;
        private BigDecimal priceWithoutSpecialPromotion;

        private BasketBuilder(List<BasketItem> basketItems) {
            this.basketItems = basketItems;
        }

        public BasketBuilder withTotalPrice() {
            priceWithoutSpecialPromotion = basketItems.stream().map(bi -> bi.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
            return this;
        }

        public BasketBuilder withSpecialPromotions(Function<List<BasketItem>, BigDecimal> function) {
            specialPromotionDiscount = function.apply(basketItems);
            priceWithSpecialPromotion = priceWithoutSpecialPromotion.subtract(specialPromotionDiscount);
            return this;
        }

        public BasketBuilder withDiscounts(Consumer<List<BasketItem>> consumer) {
            consumer.accept(basketItems);
            return this;
        }

        public Basket build() {
            return new Basket(this);
        }
    }
}


