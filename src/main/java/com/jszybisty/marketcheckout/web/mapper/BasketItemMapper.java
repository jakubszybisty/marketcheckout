package com.jszybisty.marketcheckout.web.mapper;

import com.jszybisty.marketcheckout.service.BasketItem;
import com.jszybisty.marketcheckout.web.dto.BasketItemDto;
import org.springframework.stereotype.Component;

@Component
public class BasketItemMapper implements GenericMapper<BasketItem, BasketItemDto> {

    @Override
    public BasketItem mapToEntity(BasketItemDto dto) {
        throw new UnsupportedOperationException("Just for now, because saving this entity is not yet supported");
    }

    @Override
    public BasketItemDto mapToDto(BasketItem entity) {
        final BasketItemDto basketItemDto = new BasketItemDto();
        basketItemDto.setItemName(entity.getItem().getName());
        basketItemDto.setPrice(entity.getPrice());
        basketItemDto.setQuantity(entity.getQuantity());
        return basketItemDto;
    }
}
