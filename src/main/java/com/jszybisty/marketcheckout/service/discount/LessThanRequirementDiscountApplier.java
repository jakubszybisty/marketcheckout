package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.service.BasketItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by jakub on 30.08.2017.
 */
@Component
public class LessThanRequirementDiscountApplier implements DiscountApplier {

    @Override
    public BasketItem applyDiscount(BasketItem basketItem) {
        basketItem.setPrice(basketItem.getItem().getPrice().multiply(BigDecimal.valueOf(basketItem.getQuantity())));
        return basketItem;
    }

    @Override
    public DiscountApplierStrategyType getImplementerType() {
        return DiscountApplierStrategyType.LESS_THAN_REQUIREMENT;
    }
}
