package com.jszybisty.marketcheckout.web.mapper;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.repository.ItemRepository;
import com.jszybisty.marketcheckout.web.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jakub on 30.08.2017.
 */
@Component
public class ItemMapper implements GenericMapper<Item, ItemDto> {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemMapper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public Item mapToEntity(ItemDto dto) {
        return itemRepository.findByIdAndName(dto.getId(), dto.getName());
    }

    @Override
    public ItemDto mapToDto(Item entity) {
        final ItemDto dto = new ItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
