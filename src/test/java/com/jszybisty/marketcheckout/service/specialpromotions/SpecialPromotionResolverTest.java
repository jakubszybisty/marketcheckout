package com.jszybisty.marketcheckout.service.specialpromotions;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.model.SpecialPromotion;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpecialPromotionResolverTest {

    private static final String FIRST_PROMOTION_ITEM_NAME = "fName";
    private static final String SECOND_PROMOTION_ITEM_NAME = "sName";
    private Item firstItemInPromotion;
    private Item secondItemInPromotion;
    private Item irrelevantItem;
    private SpecialPromotion promotion;
    private SpecialPromotionResolver resolver = new SpecialPromotionResolver();

    @Before
    public void setUp() {
        firstItemInPromotion = new Item();
        firstItemInPromotion.setName(FIRST_PROMOTION_ITEM_NAME);
        secondItemInPromotion = new Item();
        secondItemInPromotion.setName(SECOND_PROMOTION_ITEM_NAME);
        irrelevantItem = new Item();
        irrelevantItem.setName("irrelevantName");
        promotion = new SpecialPromotion();
        promotion.setActive(true);
        promotion.setFirstItem(firstItemInPromotion);
        promotion.setSecondItem(secondItemInPromotion);
        promotion.setDiscountPrice(BigDecimal.TEN);
    }

    @Test
    public void shouldReturnTrueWhenCheckingIfRequirementsAreMet() {
        final BasketItem basketItem = new BasketItem(firstItemInPromotion, 3L);
        final BasketItem anotherBasketItem = new BasketItem(secondItemInPromotion, 2L);
        final BasketItem irrelevantBasketItem = new BasketItem(new Item(), 20L);
        assertTrue(resolver.checkIfPromotionRequirementsAreMet(Arrays.asList(basketItem, anotherBasketItem, irrelevantBasketItem), promotion));

    }

    @Test
    public void shouldReturnFalseWhenCheckingIfRequirementsBecauseOneItemIsNotPresent() {
        final BasketItem basketItem = new BasketItem(firstItemInPromotion, 3L);
        final BasketItem irrelevantBasketItem = new BasketItem(irrelevantItem, 20L);
        assertFalse(resolver.checkIfPromotionRequirementsAreMet(Arrays.asList(basketItem, irrelevantBasketItem), promotion));
    }
}