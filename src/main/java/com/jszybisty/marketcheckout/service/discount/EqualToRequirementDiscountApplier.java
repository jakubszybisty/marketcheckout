package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.service.BasketItem;
import org.springframework.stereotype.Component;

/**
 * Created by jakub on 30.08.2017.
 */
@Component
public class EqualToRequirementDiscountApplier implements DiscountApplier {

    @Override
    public BasketItem applyDiscount(BasketItem basketItem) {
        basketItem.setPrice(basketItem.getItem().getSpecialPrice());
        return basketItem;
    }

    @Override
    public DiscountApplierStrategyType getImplementerType() {
        return DiscountApplierStrategyType.EQUAL_TO_REQUIREMENT;
    }
}
