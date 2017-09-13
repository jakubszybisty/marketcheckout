package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.service.BasketItem;
import org.springframework.stereotype.Component;

/**
 * Created by jakub on 30.08.2017.
 */
@Component
public class DiscountStrategyResolver {

    public DiscountApplierStrategyType resolveStrategyType(BasketItem basketItem) {
        if (basketItem.getQuantity() < basketItem.getItem().getSpecialPriceQuantity()) {
            return DiscountApplierStrategyType.LESS_THAN_REQUIREMENT;
        } else if (basketItem.getQuantity() == basketItem.getItem().getSpecialPriceQuantity()) {
            return DiscountApplierStrategyType.EQUAL_TO_REQUIREMENT;
        } else if (basketItem.getQuantity() > basketItem.getItem().getSpecialPriceQuantity()) {
            return DiscountApplierStrategyType.MORE_THAN_REQUIREMENT;
        } else throw new IllegalStateException("Type not supported");
    }
}
