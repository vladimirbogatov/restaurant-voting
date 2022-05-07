package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.web.controller.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurantvoting.web.controller.user.UserTestData.ADMIN_EMAIL;
import static com.example.restaurantvoting.web.controller.user.UserTestData.USER_EMAIL;
import static com.example.restaurantvoting.web.controller.vote.VotesTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + '/';


    @Test
    @WithUserDetails(value = USER_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(USER_TO_VOTE_20220430));
    }

    @Test
    void createOrUpdate() {
    }

    @Test
    void getAllVotesForRestaurant() {
    }

    @Test
    void getAllVotesOfUser() {
    }
}