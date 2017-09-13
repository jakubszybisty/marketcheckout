package com.jszybisty.marketcheckout.service.specialpromotions;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.model.SpecialPromotion;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SpecialPromotionResolver {

    public boolean checkIfPromotionRequirementsAreMet(List<BasketItem> basketItems, SpecialPromotion specialPromotion) {
        Optional<BasketItem> firstItemPresence = checkIfBasketContainsItem(basketItems, specialPromotion.getFirstItem());
        Optional<BasketItem> secondItemPresence = checkIfBasketContainsItem(basketItems, specialPromotion.getSecondItem());
        return firstItemPresence.isPresent() && secondItemPresence.isPresent();
    }

    private Optional<BasketItem> checkIfBasketContainsItem(List<BasketItem> basketItems, Item item) {
        return basketItems
                .stream()
                .filter(bi -> bi.getItem().getName().equals(item.getName()))
                .findFirst();
    }
}
