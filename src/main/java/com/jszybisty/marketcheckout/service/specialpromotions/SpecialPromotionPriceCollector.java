package com.jszybisty.marketcheckout.service.specialpromotions;

import com.jszybisty.marketcheckout.model.SpecialPromotion;
import com.jszybisty.marketcheckout.repository.SpecialPromotionRepository;
import com.jszybisty.marketcheckout.service.BasketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class SpecialPromotionPriceCollector {

    private final SpecialPromotionResolver specialPromotionResolver;
    private final SpecialPromotionRepository specialPromotionRepository;

    @Autowired
    public SpecialPromotionPriceCollector(SpecialPromotionResolver specialPromotionResolver,
                                          SpecialPromotionRepository specialPromotionRepository) {
        this.specialPromotionResolver = specialPromotionResolver;
        this.specialPromotionRepository = specialPromotionRepository;
    }

    public BigDecimal collectAllPromotionsPrices(List<BasketItem> basketItems) {
        return specialPromotionRepository.findByIsActiveTrue()
                .stream()
                .map(p -> getPromotionPriceIfApplicable(basketItems, p))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Optional<BigDecimal> getPromotionPriceIfApplicable(List<BasketItem> basketItems, SpecialPromotion specialPromotion) {
        if (specialPromotionResolver.checkIfPromotionRequirementsAreMet(basketItems, specialPromotion)) {
            return Optional.of(specialPromotion.getDiscountPrice());
        }
        return Optional.empty();
    }
}
