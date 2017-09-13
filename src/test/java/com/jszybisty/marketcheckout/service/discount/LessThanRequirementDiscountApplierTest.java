package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.junit.Test;

import java.math.BigDecimal;

import static com.jszybisty.marketcheckout.service.discount.DiscountApplierStrategyType.LESS_THAN_REQUIREMENT;
import static org.junit.Assert.assertEquals;

public class LessThanRequirementDiscountApplierTest {

    private static final BigDecimal PRICE = BigDecimal.valueOf(3);
    private static final Long REQUIREMENT_QUANTITY = 4L;
    private static final Long BASKET_QUANTITY = 3L;
    private LessThanRequirementDiscountApplier discountApplier = new LessThanRequirementDiscountApplier();

    @Test
    public void shouldApplyDiscount() {
        final Item item = new Item();
        item.setSpecialPriceQuantity(REQUIREMENT_QUANTITY);
        item.setPrice(BigDecimal.ONE);
        final BasketItem basketItem = new BasketItem(item, BASKET_QUANTITY);
        discountApplier.applyDiscount(basketItem);
        assertEquals(PRICE, basketItem.getPrice());
    }

    @Test
    public void shouldReturnImplementerType() {
        assertEquals(LESS_THAN_REQUIREMENT, discountApplier.getImplementerType());
    }

}