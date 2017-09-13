package com.jszybisty.marketcheckout.web.mapper;

import com.jszybisty.marketcheckout.service.Basket;
import com.jszybisty.marketcheckout.web.dto.BasketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasketMapper implements GenericMapper<Basket, BasketDto> {

    private final BasketItemMapper basketItemMapper;

    @Autowired
    public BasketMapper(BasketItemMapper basketItemMapper) {
        this.basketItemMapper = basketItemMapper;
    }

    @Override
    public Basket mapToEntity(BasketDto dto) {
        throw new UnsupportedOperationException("Just for now, because saving this entity is not yet supported");
    }

    @Override
    public BasketDto mapToDto(Basket entity) {
        final BasketDto basketDto = new BasketDto();
        basketDto.setBasketItemDtos(basketItemMapper.mapToDtoList(entity.getBasketItems()));
        basketDto.setSpecialPromotionDiscount(entity.getSpecialPromotionDiscount());
        basketDto.setPriceWithSpecialPromotion(entity.getPriceWithSpecialPromotion());
        basketDto.setPriceWithoutSpecialPromotion(entity.getPriceWithoutSpecialPromotion());
        return basketDto;
    }
}
