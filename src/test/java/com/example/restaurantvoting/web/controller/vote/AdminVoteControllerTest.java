package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.web.controller.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.RESTAURANT_ID;
import static com.example.restaurantvoting.web.controller.user.UserTestData.ADMIN_EMAIL;
import static com.example.restaurantvoting.web.controller.user.UserTestData.USER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminVoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminVoteController.REST_URL + '/';

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "/user/" + USER_ID + "/restaurant/" + RESTAURANT_ID)
                .param("date", "2022-05-01"))
                .andExpect(status().isOk());
    }
}