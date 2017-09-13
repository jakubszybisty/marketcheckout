package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.junit.Before;
import org.junit.Test;

import static com.jszybisty.marketcheckout.service.discount.DiscountApplierStrategyType.*;
import static org.junit.Assert.assertEquals;

public class DiscountStrategyResolverTest {

    private Item item;
    private BasketItem basketItem;
    private DiscountStrategyResolver discountStrategyResolver = new DiscountStrategyResolver();

    @Before
    public void setUp() {
        item = new Item();
        item.setSpecialPriceQuantity(3L);
    }

    @Test
    public void shouldResolveLessThanRequirementType() {
        basketItem = new BasketItem(item, 1L);
        DiscountApplierStrategyType discountApplierStrategyType = discountStrategyResolver.resolveStrategyType(basketItem);
        assertEquals(LESS_THAN_REQUIREMENT, discountApplierStrategyType);
    }

    @Test
    public void shouldResolveEqualToRequirementType() {
        basketItem = new BasketItem(item, 3L);
        DiscountApplierStrategyType discountApplierStrategyType = discountStrategyResolver.resolveStrategyType(basketItem);
        assertEquals(EQUAL_TO_REQUIREMENT, discountApplierStrategyType);
    }

    @Test
    public void shouldResolveMoreThanRequirementType() {
        basketItem = new BasketItem(item, 5L);
        DiscountApplierStrategyType discountApplierStrategyType = discountStrategyResolver.resolveStrategyType(basketItem);
        assertEquals(MORE_THAN_REQUIREMENT, discountApplierStrategyType);
    }

}