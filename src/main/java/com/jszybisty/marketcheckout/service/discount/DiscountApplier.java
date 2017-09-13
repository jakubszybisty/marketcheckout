package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.service.BasketItem;

/**
 * Created by jakub on 30.08.2017.
 */
public interface DiscountApplier {

    BasketItem applyDiscount(BasketItem basketItem);

    DiscountApplierStrategyType getImplementerType();
}
