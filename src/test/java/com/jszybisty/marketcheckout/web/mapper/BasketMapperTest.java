package com.jszybisty.marketcheckout.web.mapper;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.Basket;
import com.jszybisty.marketcheckout.service.BasketItem;
import com.jszybisty.marketcheckout.service.discount.DiscountApplierExecutor;
import com.jszybisty.marketcheckout.service.specialpromotions.SpecialPromotionPriceCollector;
import com.jszybisty.marketcheckout.web.dto.BasketDto;
import com.jszybisty.marketcheckout.web.dto.BasketItemDto;
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
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketMapperTest {

    @Mock
    private BasketItemMapper basketItemMapper;
    @Mock
    private DiscountApplierExecutor discountApplierExecutor;
    @Mock
    private SpecialPromotionPriceCollector specialPromotionPriceCollector;
    @InjectMocks
    private BasketMapper basketMapper;

    private static final String EXPECTED_EXCEPTION_MESSAGE = "Just for now, because saving this entity is not yet supported";
    private static final BigDecimal TOTAL_PRICE_WITHOUT_SPECIAL_PROMOTION = BigDecimal.valueOf(100);
    private static final BigDecimal TOTAL_PRICE_WITH_SPECIAL_PROMOTION = BigDecimal.valueOf(90);

    @Test
    public void shouldThrowExceptionWhenMappingToEntity() {
        final BasketDto dto = new BasketDto();
        try {
            basketMapper.mapToEntity(dto);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(EXPECTED_EXCEPTION_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void shouldMapToDto() {
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
    }
}