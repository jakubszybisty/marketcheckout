package com.jszybisty.marketcheckout.web.mapper;

import com.jszybisty.marketcheckout.model.Item;
import com.jszybisty.marketcheckout.repository.ItemRepository;
import com.jszybisty.marketcheckout.web.dto.ItemDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemMapperTest {

    private static final Long ITEM_ID = 2L;
    private static final String ITEM_NAME = "name";

    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private ItemMapper itemMapper;

    @Test
    public void shouldMapToEntity() {
        final Item item = new Item();
        item.setName(ITEM_NAME);
        item.setId(ITEM_ID);
        final ItemDto itemDto = new ItemDto();
        itemDto.setId(ITEM_ID);
        itemDto.setName(ITEM_NAME);

        when(itemRepository.findByIdAndName(ITEM_ID, ITEM_NAME)).thenReturn(item);
        Item mappedItem = itemMapper.mapToEntity(itemDto);
        assertEquals(mappedItem.getId(), ITEM_ID);
        assertEquals(mappedItem.getName(), ITEM_NAME);
    }

    @Test
    public void shouldMapToDto() {
        final Item item = new Item();
        item.setName(ITEM_NAME);
        item.setId(ITEM_ID);

        ItemDto itemDto = itemMapper.mapToDto(item);
        assertEquals(ITEM_NAME, itemDto.getName());
        assertEquals(ITEM_ID, item.getId());
    }

}