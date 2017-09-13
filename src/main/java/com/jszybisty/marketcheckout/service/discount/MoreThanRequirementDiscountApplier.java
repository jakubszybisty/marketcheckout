package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.service.BasketItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by jakub on 30.08.2017.
 */
@Component
public class MoreThanRequirementDiscountApplier implements DiscountApplier {

    @Override
    public BasketItem applyDiscount(BasketItem basketItem) {
        long multipleNumber = basketItem.getQuantity() / basketItem.getItem().getSpecialPriceQuantity();
        basketItem.setPrice(basketItem.getItem().getSpecialPrice().multiply(BigDecimal.valueOf(multipleNumber)));
        applyRemainderPriceIfExists(basketItem);
        return basketItem;
    }

    @Override
    public DiscountApplierStrategyType getImplementerType() {
        return DiscountApplierStrategyType.MORE_THAN_REQUIREMENT;
    }

    private void applyRemainderPriceIfExists(BasketItem basketItem) {
        long remainder = getRemainderFromDivision(basketItem);
        if (remainder > 0) {
            addRemainderValue(basketItem, remainder);
        }
    }

    private long getRemainderFromDivision(BasketItem basketItem) {
        return basketItem.getQuantity() % basketItem.getItem().getSpecialPriceQuantity();
    }

    private void addRemainderValue(BasketItem basketItem, long remainder) {
        basketItem.setPrice(basketItem.getPrice().add((basketItem.getItem().getPrice().multiply(BigDecimal.valueOf(remainder)))));
    }
}
