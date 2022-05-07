package com.example.restaurantvoting.web.controller.dish;

import com.example.restaurantvoting.web.controller.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.controller.dish.DishTestData.*;
import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.RESTAURANT1;
import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.RESTAURANT_MATCHER;
import static com.example.restaurantvoting.web.controller.user.UserTestData.USER_EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishController.REST_URL+'/';

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL+DISH_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH1_RESTAURANT1));
    }

    @Test
    void getAnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_ID))
                .andExpect(status().isUnauthorized());
    }
}