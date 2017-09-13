package com.jszybisty.marketcheckout.service;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.discount.DiscountApplierExecutor;
import com.jszybisty.marketcheckout.service.specialpromotions.SpecialPromotionPriceCollector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BasketCheckoutServiceTest {

    private static final BigDecimal PRICE_WITHOUT_SPECIAL_PROMOTION = BigDecimal.valueOf(200);
    private static final BigDecimal PRICE_WITH_SPECIAL_PROMOTION = BigDecimal.valueOf(190);
    private static final BigDecimal SPECIAL_PROMOTION_DISCOUNT = BigDecimal.valueOf(10);

    @Mock
    private DiscountApplierExecutor discountApplierExecutor;
    @Mock
    private BasketItemCollector basketItemCollector;
    @Mock
    private SpecialPromotionPriceCollector specialPromotionPriceCollector;
    @InjectMocks
    private BasketCheckoutService basketCheckoutService;

    @Test
    public void shouldCheckoutBasket() {
        final Item item = new Item();
        final Item anotherItem = new Item();
        final List<Item> items = Arrays.asList(item, anotherItem, anotherItem);
        final BasketItem firstBasketItem = new BasketItem(item, 1L);
        firstBasketItem.setPrice(BigDecimal.valueOf(50));
        final BasketItem secondBasketItem = new BasketItem(anotherItem, 2L);
        secondBasketItem.setPrice(BigDecimal.valueOf(150));
        final List<BasketItem> basketItems = Arrays.asList(firstBasketItem, secondBasketItem);

        when(basketItemCollector.collectBySameBasketItems(items)).thenReturn(basketItems);
        when(specialPromotionPriceCollector.collectAllPromotionsPrices(basketItems)).thenReturn(BigDecimal.TEN);
        Basket basket = basketCheckoutService.checkoutBasket(items);

        verify(discountApplierExecutor, times(1)).applyDiscounts(basketItems);
        assertEquals(PRICE_WITH_SPECIAL_PROMOTION, basket.getPriceWithSpecialPromotion());
        assertEquals(PRICE_WITHOUT_SPECIAL_PROMOTION, basket.getPriceWithoutSpecialPromotion());
        assertEquals(SPECIAL_PROMOTION_DISCOUNT, basket.getSpecialPromotionDiscount());
        assertEquals(2, basket.getBasketItems().size());
    }
}