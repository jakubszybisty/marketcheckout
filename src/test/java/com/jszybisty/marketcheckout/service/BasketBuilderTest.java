package com.jszybisty.marketcheckout.service;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.discount.DiscountApplierExecutor;
import com.jszybisty.marketcheckout.service.specialpromotions.SpecialPromotionPriceCollector;
import com.jszybisty.marketcheckout.web.dto.BasketDto;
import com.jszybisty.marketcheckout.web.dto.BasketItemDto;
import com.jszybisty.marketcheckout.web.mapper.BasketItemMapper;
import com.jszybisty.marketcheckout.web.mapper.BasketMapper;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by jakub on 13.09.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BasketBuilderTest {

    @Mock
    private BasketItemMapper basketItemMapper;
    @Mock
    private DiscountApplierExecutor discountApplierExecutor;
    @Mock
    private SpecialPromotionPriceCollector specialPromotionPriceCollector;
    @InjectMocks
    private BasketMapper basketMapper;

    private static final BigDecimal TOTAL_PRICE_WITHOUT_SPECIAL_PROMOTION = BigDecimal.valueOf(100);
    private static final BigDecimal TOTAL_PRICE_WITH_SPECIAL_PROMOTION = BigDecimal.valueOf(90);

    @Test
    public void shouldBuildBasket() {
        when(specialPromotionPriceCollector.collectAllPromotionsPrices(any())).thenReturn(BigDecimal.TEN);
        final BasketItem basketItem = new BasketItem(new Item(), 2L);
        basketItem.setPrice(BigDecimal.valueOf(100));
        final List<BasketItem> basketItemList = Collections.singletonList(basketItem);
        final Basket basket = Basket.of(basketItemList)
                .withDiscounts(discountApplierExecutor::applyDiscounts)
                .withTotalPrice()
                .withSpecialPromotions(specialPromotionPriceCollector::collectAllPromotionsPrices)
                .build();

        when(basketItemMapper.mapToDtoList(basketItemList)).thenReturn(Arrays.asList(new BasketItemDto()));
        BasketDto basketDto = basketMapper.mapToDto(basket);

        assertEquals(1, basketDto.getBasketItemDtos().size());
        assertEquals(TOTAL_PRICE_WITHOUT_SPECIAL_PROMOTION, basketDto.getPriceWithoutSpecialPromotion());
        assertEquals(TOTAL_PRICE_WITH_SPECIAL_PROMOTION, basket.getPriceWithSpecialPromotion());
        assertEquals(BigDecimal.TEN, basket.getSpecialPromotionDiscount());
        verify(discountApplierExecutor, timeout(1)).applyDiscounts(basketItemList);
    }
}
