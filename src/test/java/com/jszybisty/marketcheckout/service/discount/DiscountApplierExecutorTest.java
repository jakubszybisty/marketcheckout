package com.jszybisty.marketcheckout.service.discount;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static com.jszybisty.marketcheckout.service.discount.DiscountApplierStrategyType.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DiscountApplierExecutorTest {

    private Item item;
    private BasketItem basketItem;

    @Mock
    private DiscountStrategyResolver discountStrategyResolver;
    @Mock
    private LessThanRequirementDiscountApplier lessThanRequirementDiscountApplier;
    @Mock
    private EqualToRequirementDiscountApplier equalToRequirementDiscountApplier;
    @Mock
    private MoreThanRequirementDiscountApplier moreThanRequirementDiscountApplier;
    private DiscountApplierExecutor discountApplierExecutor;

    @Before
    public void setUp() {
        item = new Item();
        basketItem = new BasketItem(item, 2L);

        when(lessThanRequirementDiscountApplier.getImplementerType()).thenReturn(LESS_THAN_REQUIREMENT);
        when(moreThanRequirementDiscountApplier.getImplementerType()).thenReturn(MORE_THAN_REQUIREMENT);
        when(equalToRequirementDiscountApplier.getImplementerType()).thenReturn(EQUAL_TO_REQUIREMENT);

        discountApplierExecutor = new DiscountApplierExecutor(
                Arrays.asList(lessThanRequirementDiscountApplier, equalToRequirementDiscountApplier, moreThanRequirementDiscountApplier),
                discountStrategyResolver);
    }

    @Test
    public void shouldApplyDiscountWhenTypeIsLessThanRequirement() {
        when(discountStrategyResolver.resolveStrategyType(basketItem)).thenReturn(LESS_THAN_REQUIREMENT);
        discountApplierExecutor.applyDiscounts(Collections.singletonList(basketItem));
        verify(lessThanRequirementDiscountApplier, times(1)).applyDiscount(basketItem);
    }

    @Test
    public void shouldApplyDiscountWhenTypeIsEqualToRequirement() {
        when(discountStrategyResolver.resolveStrategyType(basketItem)).thenReturn(EQUAL_TO_REQUIREMENT);
        discountApplierExecutor.applyDiscounts(Collections.singletonList(basketItem));
        verify(equalToRequirementDiscountApplier, times(1)).applyDiscount(basketItem);
    }

    @Test
    public void shouldApplyDiscountWhenTypeIsMoreThanRequirement() {
        when(discountStrategyResolver.resolveStrategyType(basketItem)).thenReturn(MORE_THAN_REQUIREMENT);
        discountApplierExecutor.applyDiscounts(Collections.singletonList(basketItem));
        verify(moreThanRequirementDiscountApplier, times(1)).applyDiscount(basketItem);
    }

    @Test
    public void shouldApplyDiscountsWhenThereAreMultipleTypes() {
        final BasketItem firstItem = new BasketItem(item, 1L);
        final BasketItem secondItem = new BasketItem(item, 2L);
        final BasketItem thirdItem = new BasketItem(item, 3L);
        final BasketItem fourthItem = new BasketItem(item, 1L);

        when(discountStrategyResolver.resolveStrategyType(firstItem)).thenReturn(LESS_THAN_REQUIREMENT);
        when(discountStrategyResolver.resolveStrategyType(secondItem)).thenReturn(EQUAL_TO_REQUIREMENT);
        when(discountStrategyResolver.resolveStrategyType(thirdItem)).thenReturn(MORE_THAN_REQUIREMENT);
        when(discountStrategyResolver.resolveStrategyType(fourthItem)).thenReturn(LESS_THAN_REQUIREMENT);

        discountApplierExecutor.applyDiscounts(Arrays.asList(firstItem, secondItem, thirdItem, fourthItem));
        verify(lessThanRequirementDiscountApplier, times(2)).applyDiscount(any(BasketItem.class));
        verify(equalToRequirementDiscountApplier, times(1)).applyDiscount(secondItem);
        verify(moreThanRequirementDiscountApplier, times(1)).applyDiscount(thirdItem);
    }

}