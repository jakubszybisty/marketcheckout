package com.jszybisty.marketcheckout.web;

import com.jszybisty.marketcheckout.service.BasketCheckoutService;
import com.jszybisty.marketcheckout.web.dto.BasketDto;
import com.jszybisty.marketcheckout.web.dto.ItemDto;
import com.jszybisty.marketcheckout.web.mapper.BasketMapper;
import com.jszybisty.marketcheckout.web.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.jszybisty.marketcheckout.web.ApiUrl.BASKET;

@RestController
@RequestMapping(BASKET)
public class BasketController {

    private final BasketCheckoutService basketCheckoutService;
    private final ItemMapper itemMapper;
    private final BasketMapper basketMapper;

    @Autowired
    public BasketController(BasketCheckoutService basketCheckoutService, ItemMapper itemMapper, BasketMapper basketMapper) {
        this.basketCheckoutService = basketCheckoutService;
        this.itemMapper = itemMapper;
        this.basketMapper = basketMapper;
    }

    @PostMapping
    public ResponseEntity<BasketDto> checkoutBasket(final @Valid @RequestBody List<ItemDto> items) {
        BasketDto basketDto = basketMapper.mapToDto(basketCheckoutService.checkoutBasket(itemMapper.mapToEntityList(items)));
        return ResponseEntity.ok(basketDto);
    }
}
