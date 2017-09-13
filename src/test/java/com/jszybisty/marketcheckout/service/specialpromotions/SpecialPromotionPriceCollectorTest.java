package com.jszybisty.marketcheckout.service.specialpromotions;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.model.SpecialPromotion;
import com.jszybisty.marketcheckout.repository.SpecialPromotionRepository;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpecialPromotionPriceCollectorTest {

    @Mock
    private SpecialPromotionResolver specialPromotionResolver;
    @Mock
    private SpecialPromotionRepository specialPromotionRepository;
    @InjectMocks
    private SpecialPromotionPriceCollector collector;

    @Test
    public void shouldCollectAllPromotionsPrices() {
        final SpecialPromotion specialPromotion = new SpecialPromotion();
        specialPromotion.setActive(true);
        specialPromotion.setDiscountPrice(BigDecimal.TEN);
        final SpecialPromotion anotherSpecialPromotion = new SpecialPromotion();
        anotherSpecialPromotion.setActive(true);
        anotherSpecialPromotion.setDiscountPrice(BigDecimal.ONE);
        final SpecialPromotion notApplicableSpecialPromotion = new SpecialPromotion();
        notApplicableSpecialPromotion.setActive(false);
        notApplicableSpecialPromotion.setDiscountPrice(BigDecimal.valueOf(100000));
        final List<BasketItem> basketItems = Collections.singletonList(new BasketItem(new Item(), 2L));

        when(specialPromotionRepository.findByIsActiveTrue()).thenReturn(Arrays.asList(specialPromotion, anotherSpecialPromotion, notApplicableSpecialPromotion));
        when(specialPromotionResolver.checkIfPromotionRequirementsAreMet(basketItems, specialPromotion)).thenReturn(Boolean.TRUE);
        when(specialPromotionResolver.checkIfPromotionRequirementsAreMet(basketItems, anotherSpecialPromotion)).thenReturn(Boolean.TRUE);
        when(specialPromotionResolver.checkIfPromotionRequirementsAreMet(basketItems, notApplicableSpecialPromotion)).thenReturn(Boolean.FALSE);

        BigDecimal collectedPrice = collector.collectAllPromotionsPrices(basketItems);
        assertEquals(BigDecimal.valueOf(11), collectedPrice);
    }
}