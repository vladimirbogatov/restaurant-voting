package com.example.restaurantvoting;

import com.example.restaurantvoting.model.Votes;

import java.time.LocalDate;

import static com.example.restaurantvoting.RestaurantsTestData.*;
import static com.example.restaurantvoting.UserTestData.ADMIN;
import static com.example.restaurantvoting.UserTestData.USER;

public class VotesTestData {
    public static final Votes USER_VOTES_TODAY = new Votes(USER, RESTAURANT1, LocalDate.now());
    public static final Votes USER_VOTES_YESTERDAY = new Votes(USER, RESTAURANT2, LocalDate.now().minusDays(1));
    public static final Votes ADMIN_VOTES_TODAY = new Votes(ADMIN, RESTAURANT3, LocalDate.now());
    public static final Votes ADMIN_VOTES_2DAYS_BEFORE = new Votes(ADMIN, RESTAURANT2, LocalDate.now().minusDays(1));
}
