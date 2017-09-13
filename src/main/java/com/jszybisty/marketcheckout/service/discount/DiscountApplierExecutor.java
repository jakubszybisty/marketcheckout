package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.service.BasketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jakub on 30.08.2017.
 */
@Component
public class DiscountApplierExecutor {

    private final Map<DiscountApplierStrategyType, DiscountApplier> strategies = new EnumMap<>(DiscountApplierStrategyType.class);
    private final DiscountStrategyResolver discountStrategyResolver;

    @Autowired
    public DiscountApplierExecutor(final List<DiscountApplier> discountApplierList, final DiscountStrategyResolver discountStrategyResolver) {
        this.discountStrategyResolver = discountStrategyResolver;
        discountApplierList.forEach(applier -> strategies.put(applier.getImplementerType(), applier));
    }

    public void applyDiscounts(final List<BasketItem> basketItems) {
        basketItems.forEach(basketItem -> {
            final DiscountApplier discountApplier = strategies.get(discountStrategyResolver.resolveStrategyType(basketItem));
            discountApplier.applyDiscount(basketItem);
        });
    }
}
