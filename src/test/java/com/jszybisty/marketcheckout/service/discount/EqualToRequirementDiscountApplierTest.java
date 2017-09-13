package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.junit.Test;

import java.math.BigDecimal;

import static com.jszybisty.marketcheckout.service.discount.DiscountApplierStrategyType.EQUAL_TO_REQUIREMENT;
import static org.junit.Assert.assertEquals;

public class EqualToRequirementDiscountApplierTest {

    private static final BigDecimal DISCOUNTED_PRICE = BigDecimal.TEN;
    private static final Long REQUIREMENT_QUANTITY = 4L;
    private static final Long BASKET_QUANTITY = 4L;
    private EqualToRequirementDiscountApplier discountApplier = new EqualToRequirementDiscountApplier();

    @Test
    public void shouldApplyDiscount() {
        final Item item = new Item();
        item.setSpecialPriceQuantity(REQUIREMENT_QUANTITY);
        item.setSpecialPrice(BigDecimal.TEN);
        final BasketItem basketItem = new BasketItem(item, BASKET_QUANTITY);
        discountApplier.applyDiscount(basketItem);
        assertEquals(DISCOUNTED_PRICE, basketItem.getPrice());
    }

    @Test
    public void shouldReturnImplementerType() {
        assertEquals(EQUAL_TO_REQUIREMENT, discountApplier.getImplementerType());
    }

}