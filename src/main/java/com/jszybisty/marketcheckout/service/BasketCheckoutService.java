package com.jszybisty.marketcheckout.service;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.discount.DiscountApplierExecutor;
import com.jszybisty.marketcheckout.service.specialpromotions.SpecialPromotionPriceCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 30.08.2017.
 */
@Service
public class BasketCheckoutService {

    private final DiscountApplierExecutor discountApplierExecutor;
    private final BasketItemCollector basketItemCollector;
    private final SpecialPromotionPriceCollector specialPromotionPriceCollector;

    @Autowired
    public BasketCheckoutService(BasketItemCollector basketItemCollector,
                                 DiscountApplierExecutor discountApplierExecutor,
                                 SpecialPromotionPriceCollector specialPromotionPriceCollector) {
        this.discountApplierExecutor = discountApplierExecutor;
        this.basketItemCollector = basketItemCollector;
        this.specialPromotionPriceCollector = specialPromotionPriceCollector;
    }

    public Basket checkoutBasket(List<Item> items) {
        final List<BasketItem> basketItems = basketItemCollector.collectBySameBasketItems(items);
        return Basket
                .of(basketItems)
                .withDiscounts(discountApplierExecutor::applyDiscounts)
                .withTotalPrice()
                .withSpecialPromotions(specialPromotionPriceCollector::collectAllPromotionsPrices)
                .build();
    }
}
