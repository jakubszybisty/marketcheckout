package com.jszybisty.marketcheckout.web;

import com.jszybisty.marketcheckout.web.dto.BasketDto;
import com.jszybisty.marketcheckout.web.dto.ItemDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasketControllerIT {

    private static final String URL_PREFIX = "http://localhost:";
    private static final String URL_SUFIX = "/api/v1/basket";
    private static final BigDecimal SPECIAL_DISCOUNT = BigDecimal.TEN;
    private static final BigDecimal PRICE_WITH_SPECIAL_PROMOTION = BigDecimal.valueOf(100);
    private static final BigDecimal PRICE_WITHOUT_SPECIAL_PROMOTION = BigDecimal.valueOf(110);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldCheckoutBasket() {
        final ItemDto itemA = new ItemDto();
        itemA.setName("A");
        itemA.setId(1L);
        final ItemDto itemB = new ItemDto();
        itemB.setName("B");
        itemB.setId(2L);
        final ItemDto itemC = new ItemDto();
        itemC.setName("C");
        itemC.setId(3L);
        final List<ItemDto> items = Arrays.asList(itemA, itemA, itemA, itemB, itemC);
        ResponseEntity<BasketDto> response = testRestTemplate.postForEntity(URL_PREFIX + port + URL_SUFIX, items, BasketDto.class);
        BasketDto basketDto = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(PRICE_WITHOUT_SPECIAL_PROMOTION.longValue(), basketDto.getPriceWithoutSpecialPromotion().longValue());
        assertEquals(PRICE_WITH_SPECIAL_PROMOTION.longValue(), basketDto.getPriceWithSpecialPromotion().stripTrailingZeros().longValue());
        assertEquals(SPECIAL_DISCOUNT.longValue(), basketDto.getSpecialPromotionDiscount().stripTrailingZeros().longValue());
    }
}
