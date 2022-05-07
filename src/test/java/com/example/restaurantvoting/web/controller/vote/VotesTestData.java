package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.model.Vote;

import java.time.LocalDate;

import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.*;
import static com.example.restaurantvoting.web.controller.user.UserTestData.ADMIN;
import static com.example.restaurantvoting.web.controller.user.UserTestData.USER;

public class VotesTestData {
    public static final Vote USER_VOTE_TODAY = new Vote(USER, RESTAURANT1, LocalDate.now());
    public static final Vote USER_VOTE_YESTERDAY = new Vote(USER, RESTAURANT2, LocalDate.now().minusDays(1));
    public static final Vote ADMIN_VOTE_TODAY = new Vote(ADMIN, RESTAURANT3, LocalDate.now());
    public static final Vote ADMIN_VOTE_2_DAYS_BEFORE = new Vote(ADMIN, RESTAURANT2, LocalDate.now().minusDays(1));
}
