package com.jszybisty.marketcheckout.web.mapper;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.service.BasketItem;
import com.jszybisty.marketcheckout.web.dto.BasketItemDto;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BasketItemMapperTest {

    private BasketItemMapper basketItemMapper = new BasketItemMapper();

    private static final String EXPECTED_EXCEPTION_MESSAGE = "Just for now, because saving this entity is not yet supported";
    private static final String ITEM_NAME = "itemName";
    private static final BigDecimal BASKET_ITEM_PRICE = BigDecimal.ONE;
    private static final Long BASKET_ITEM_QUANTITY = 2L;

    @Test
    public void shouldThrowExceptionWhenMappingToEntity() {
        final BasketItemDto dto = new BasketItemDto();
        try {
            basketItemMapper.mapToEntity(dto);
            fail();
        } catch (UnsupportedOperationException e) {
            assertEquals(EXPECTED_EXCEPTION_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void shouldMapToDto() {
        final Item item = new Item();
        item.setName(ITEM_NAME);
        final BasketItem basketItem = new BasketItem(item, BASKET_ITEM_QUANTITY);
        basketItem.setPrice(BASKET_ITEM_PRICE);

        BasketItemDto basketItemDto = basketItemMapper.mapToDto(basketItem);

        assertEquals(basketItemDto.getItemName(), ITEM_NAME);
        assertEquals(basketItemDto.getQuantity(), BASKET_ITEM_QUANTITY);
        assertEquals(basketItemDto.getPrice(), BASKET_ITEM_PRICE);
    }
}