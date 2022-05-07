package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.model.User;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.to.VoteTo;
import com.example.restaurantvoting.web.controller.MatcherFactory;
import org.hibernate.validator.internal.engine.ValidatorImpl;

import java.time.LocalDate;

import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.*;
import static com.example.restaurantvoting.web.controller.user.UserTestData.ADMIN;
import static com.example.restaurantvoting.web.controller.user.UserTestData.USER;

public class VotesTestData {
    static final int VOTE_ID = 1;
    public static final VoteTo ADMIN_TO_VOTE_20220430 = new VoteTo(VOTE_ID, RESTAURANT3.getId(), LocalDate.of(2022,04,30));
    public static final VoteTo USER_TO_VOTE_20220430 = new VoteTo(VOTE_ID+1, RESTAURANT2.getId(), LocalDate.of(2022,04,30));
/*    public static final Vote USER_VOTE_YESTERDAY = new Vote(USER, RESTAURANT2, LocalDate.now().minusDays(1));

    public static final Vote ADMIN_VOTE_2_DAYS_BEFORE = new Vote(ADMIN, RESTAURANT2, LocalDate.now().minusDays(1));*/
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);
}
