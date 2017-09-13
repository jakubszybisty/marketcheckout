package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.junit.Test;

import java.math.BigDecimal;

import static com.jszybisty.marketcheckout.service.discount.DiscountApplierStrategyType.MORE_THAN_REQUIREMENT;
import static org.junit.Assert.assertEquals;

public class MoreThanRequirementDiscountApplierTest {

    private static final Long REQUIREMENT_QUANTITY = 4L;
    private static final Long BASKET_QUANTITY_WITH_REMAINDER = 10L;
    private static final Long BASKET_QUANTITY_WITHOUT_REMAINDER = 12L;
    private static final BigDecimal DISCOUNTED_PRICE_WITH_REMAINDER = BigDecimal.valueOf(50);
    private static final BigDecimal DISCOUNTED_PRICE_WITHOUT_REMAINDER = BigDecimal.valueOf(30);
    private MoreThanRequirementDiscountApplier discountApplier = new MoreThanRequirementDiscountApplier();

    @Test
    public void shouldApplyDiscountWithRemainder() {
        final Item item = new Item();
        item.setSpecialPriceQuantity(REQUIREMENT_QUANTITY);
        item.setSpecialPrice(BigDecimal.TEN);
        item.setPrice(BigDecimal.valueOf(15));
        final BasketItem basketItem = new BasketItem(item, BASKET_QUANTITY_WITH_REMAINDER);
        discountApplier.applyDiscount(basketItem);
        assertEquals(DISCOUNTED_PRICE_WITH_REMAINDER, basketItem.getPrice());
    }

    @Test
    public void shouldApplyDiscountWithoutRemainder() {
        final Item item = new Item();
        item.setSpecialPriceQuantity(REQUIREMENT_QUANTITY);
        item.setSpecialPrice(BigDecimal.TEN);
        item.setPrice(BigDecimal.valueOf(15));
        final BasketItem basketItem = new BasketItem(item, BASKET_QUANTITY_WITHOUT_REMAINDER);
        discountApplier.applyDiscount(basketItem);
        assertEquals(DISCOUNTED_PRICE_WITHOUT_REMAINDER, basketItem.getPrice());
    }

    @Test
    public void shouldReturnImplementerType() {
        assertEquals(MORE_THAN_REQUIREMENT, discountApplier.getImplementerType());
    }

}