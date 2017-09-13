package com.jszybisty.marketcheckout.service;

import com.jszybisty.marketcheckout.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class BasketItemCollector {

    public List<BasketItem> collectBySameBasketItems(List<Item> items) {
        return countOccurrences(items).entrySet().stream().map(e -> new BasketItem(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    private Map<Item, Long> countOccurrences(List<Item> items) {
        return items.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
