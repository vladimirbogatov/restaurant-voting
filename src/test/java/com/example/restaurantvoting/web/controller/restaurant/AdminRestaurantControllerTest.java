package com.example.restaurantvoting.web.controller.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.util.JsonUtil;
import com.example.restaurantvoting.web.controller.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.*;
import static com.example.restaurantvoting.web.controller.user.UserTestData.ADMIN_EMAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantController.REST_URL + '/';

    @Autowired
    RestaurantRepository repository;

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void create() throws Exception {
        Restaurant newRestaurant = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)));

        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(repository.getById(newId), newRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void update() throws Exception {
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(getUpdated())))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID)).andExpect(status().isNoContent());
    }

    @Test
    void deleteAnauth() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_ID)).andExpect(status().isUnauthorized());
    }
}