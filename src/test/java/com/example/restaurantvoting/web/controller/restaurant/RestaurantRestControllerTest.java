package com.example.restaurantvoting.web.controller.restaurant;

import com.example.restaurantvoting.web.controller.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.controller.dish.DishTestData.DISH_MATCHER;
import static com.example.restaurantvoting.web.controller.dish.DishTestData.MENU_RESTAURANT1;
import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.*;
import static com.example.restaurantvoting.web.controller.user.UserTestData.USER_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT1));
    }

    @Test
    void getAnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID)).andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANTS));
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void getMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID + "/menu"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(DISH_MATCHER.contentJson(MENU_RESTAURANT1));
    }
}