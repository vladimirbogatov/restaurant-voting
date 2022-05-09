package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.repository.VotesRepository;
import com.example.restaurantvoting.to.VoteTo;
import com.example.restaurantvoting.util.VotesUtil;
import com.example.restaurantvoting.util.time.DateTimeProvider;
import com.example.restaurantvoting.web.controller.AbstractControllerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.example.restaurantvoting.util.VotesUtil.THRESHOLD_TIME;
import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.RESTAURANT1;
import static com.example.restaurantvoting.web.controller.user.UserTestData.ADMIN_EMAIL;
import static com.example.restaurantvoting.web.controller.user.UserTestData.USER_EMAIL;
import static com.example.restaurantvoting.web.controller.vote.VotesTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + '/';

    @Autowired
    VotesRepository repository;

    @AfterEach
    void cleanUp() {
        DateTimeProvider.getInstance().resetTimeToDefault();
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + 2))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(USER_TO_VOTE_20220430));
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void createNewIfBeforeThreshold() throws Exception {
        LocalDate nowDate = LocalDate.now();
        LocalTime customTime = THRESHOLD_TIME.minusMinutes(1);
        LocalDateTime customDateTime = LocalDateTime.of(nowDate, customTime);
        DateTimeProvider.getInstance().setDateTime(customDateTime);
        int restaurantId = 3;
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", Integer.toString(restaurantId)))
                .andExpect(status().isCreated())
                .andDo(print());

        VoteTo created = VOTE_TO_MATCHER.readFromJson(action);
        int newId = created.getId();
        VoteTo newVoteTo = new VoteTo(newId, restaurantId, nowDate);
        assertEquals(created, newVoteTo);
        assertEquals(newVoteTo, VotesUtil.createTo(repository.getById(newId)));
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void createNewIfAfterThreshold() throws Exception {
        LocalDate nowDate = LocalDate.now();
        LocalTime customTime = THRESHOLD_TIME.plusMinutes(1);
        LocalDateTime customDateTime = LocalDateTime.of(nowDate, customTime);
        DateTimeProvider.getInstance().setDateTime(customDateTime);
        int restaurantId = 3;
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", Integer.toString(restaurantId)))
                .andExpect(status().isCreated())
                .andDo(print());

        VoteTo created = VOTE_TO_MATCHER.readFromJson(action);
        int newId = created.getId();
        VoteTo newVoteTo = new VoteTo(newId, restaurantId, nowDate);
        assertEquals(created, newVoteTo);
        assertEquals(newVoteTo, VotesUtil.createTo(repository.getById(newId)));

    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void createExistingBeforeThreshold() throws Exception {
        VoteTo existing = VotesTestData.getExisting();
        VoteTo updated = VotesTestData.getUpdate();

        LocalDate nowDate = existing.getDate();
        LocalTime customTime = THRESHOLD_TIME.minusMinutes(1);
        LocalDateTime customDateTime = LocalDateTime.of(nowDate, customTime);
        DateTimeProvider.getInstance().setDateTime(customDateTime);

        int restaurantId = updated.getRestaurant_id();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", Integer.toString(restaurantId)))
                .andExpect(status().isCreated())
                .andDo(print());

        assertEquals(updated, VotesUtil.createTo(repository.getById(existing.getId())));
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void createExistingAfterThreshold() throws Exception {
        VoteTo existing = VotesTestData.getExisting();
        VoteTo updated = VotesTestData.getUpdate();

        LocalDate nowDate = existing.getDate();
        LocalTime customTime = THRESHOLD_TIME.plusMinutes(1);
        LocalDateTime customDateTime = LocalDateTime.of(nowDate, customTime);
        DateTimeProvider.getInstance().setDateTime(customDateTime);

        int restaurantId = updated.getRestaurant_id();
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", Integer.toString(restaurantId)))
                .andExpect(status().isCreated())
                .andDo(print());

        assertEquals(existing, VotesUtil.createTo(repository.getById(existing.getId())));
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void getAllVotesForRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "restaurant/" + RESTAURANT1.id())
                .param("startDate", "")
                .param("endDate", ""))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(VOTE_FOR_RESTAURANT1));
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void getAllVotesOfUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "user")
                .param("startDate", "")
                .param("endDate", ""))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(VOTE_TO_MATCHER.contentJson(VOTE_OF_ADMIN));
    }
}