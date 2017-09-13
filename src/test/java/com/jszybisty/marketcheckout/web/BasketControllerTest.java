package com.jszybisty.marketcheckout.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jszybisty.marketcheckout.web.dto.ItemDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.jszybisty.marketcheckout.web.ApiUrl.BASKET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCheckoutBasketAndReturnStatusCode200() throws Exception {
        final ItemDto firstItem = new ItemDto();
        firstItem.setName("name");
        firstItem.setId(1L);
        final ItemDto secondItem = new ItemDto();
        secondItem.setName("secondName");
        secondItem.setId(2L);
        List<ItemDto> itemDtoList = Arrays.asList(firstItem, secondItem);

        mockMvc.perform(post(BASKET)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeToJSON(itemDtoList)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCheckoutBasketAndReturnStatusCode500WhenRequestBodyIsEmpty() throws Exception {
        mockMvc.perform(post(BASKET)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new byte[0]))
                .andExpect(status().isBadRequest());
    }

    private String writeToJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}