package com.jszybisty.marketcheckout.service;

import com.jszybisty.marketcheckout.model.Item;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasketItemCollectorTest {

    private static final String BICYCLE = "bicycle";
    private static final String COMPUTER = "computer";
    private static final String COMPILER = "compiler";
    private BasketItemCollector collector = new BasketItemCollector();

    @Test
    public void shouldCollectBySameBasketItems() {
        final Item bicycle = new Item();
        bicycle.setId(1L);
        bicycle.setPrice(BigDecimal.TEN);
        bicycle.setSpecialPrice(BigDecimal.ONE);
        bicycle.setName(BICYCLE);
        bicycle.setSpecialPriceQuantity(2L);

        final Item computer = new Item();
        computer.setId(2L);
        computer.setPrice(BigDecimal.TEN);
        computer.setSpecialPrice(BigDecimal.ONE);
        computer.setName(COMPUTER);
        computer.setSpecialPriceQuantity(2L);

        final Item compiler = new Item();
        compiler.setId(3L);
        compiler.setPrice(BigDecimal.TEN);
        compiler.setSpecialPrice(BigDecimal.ONE);
        compiler.setName(COMPILER);
        compiler.setSpecialPriceQuantity(2L);

        final List<Item> items = Arrays.asList(bicycle, compiler, compiler, computer, bicycle, bicycle);
        List<BasketItem> basketItems = collector.collectBySameBasketItems(items);
        assertEquals(3, basketItems.size());
        assertEquals(Long.valueOf(2), basketItems.stream().filter(bi -> bi.getItem().getName().equals(COMPILER)).findFirst().get().getQuantity());
        assertEquals(Long.valueOf(3), basketItems.stream().filter(bi -> bi.getItem().getName().equals(BICYCLE)).findFirst().get().getQuantity());
        assertEquals(Long.valueOf(1), basketItems.stream().filter(bi -> bi.getItem().getName().equals(COMPUTER)).findFirst().get().getQuantity());
    }

}