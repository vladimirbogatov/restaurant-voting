package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.to.VoteTo;
import com.example.restaurantvoting.web.controller.MatcherFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData.*;

public class VotesTestData {
    static final int VOTE_ID = 1;

    public static final LocalDate DATE_30042022 = LocalDate.of(2022, 4, 30);
    public static final LocalDate DATE_01052022 = LocalDate.of(2022, 5, 1);
    public static final LocalDate DATE_02052022 = LocalDate.of(2022, 5, 2);
    public static final VoteTo ADMIN_TO_VOTE_20220430 = new VoteTo(VOTE_ID, RESTAURANT3.getId(), DATE_30042022);
    public static final VoteTo USER_TO_VOTE_20220430 = new VoteTo(VOTE_ID + 1, RESTAURANT2.getId(), DATE_30042022);
    public static final VoteTo ADMIN_TO_VOTE_20220501 = new VoteTo(VOTE_ID + 2, RESTAURANT1.getId(), DATE_01052022);
    public static final VoteTo USER_TO_VOTE_20220501 = new VoteTo(VOTE_ID + 3, RESTAURANT1.getId(), DATE_01052022);
    public static final VoteTo ADMIN_TO_VOTE_20220502 = new VoteTo(VOTE_ID + 4, RESTAURANT2.getId(), DATE_02052022);
    public static final List<VoteTo> VOTE_FOR_RESTAURANT1 = Arrays.asList(ADMIN_TO_VOTE_20220501, USER_TO_VOTE_20220501);
    public static final List<VoteTo> VOTE_OF_ADMIN = Arrays.asList(ADMIN_TO_VOTE_20220502, ADMIN_TO_VOTE_20220501, ADMIN_TO_VOTE_20220430);
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static VoteTo getExisting() {
        return USER_TO_VOTE_20220430;
    }

    public static VoteTo getUpdate() {
        return new VoteTo(2, 3, LocalDate.of(2022, 4, 30));
    }
}
