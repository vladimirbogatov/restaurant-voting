package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.VotesTestData;
import com.example.restaurantvoting.model.Votes;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.restaurantvoting.RestaurantsTestData.RESTAURANT1;
import static com.example.restaurantvoting.RestaurantsTestData.RESTAURANT2;
import static com.example.restaurantvoting.UserTestData.ADMIN;
import static com.example.restaurantvoting.UserTestData.USER;
import static com.example.restaurantvoting.VotesTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class VotesRepositoryTest extends AbstractTestClass {

    @Autowired
    VotesRepository votesRepository;

    @Test
    void save() {
        Votes actual = votesRepository.save(USER_VOTES_TODAY);
        assertThat(actual).isNotNull();
    }

    @Test
    void delete() {
        votesRepository.delete(USER.getId(), RESTAURANT1.getId(), LocalDate.now());
        assertThat(votesRepository.findAll()).usingRecursiveComparison().ignoringFields("User.password", "User.registered")
                .ignoringCollectionOrder().isEqualTo(List.of(new Votes(ADMIN, RESTAURANT2, LocalDate.now())));
    }

    @Test
    void getAllVotesForRestaurants() {
        votesRepository.save(USER_VOTES_TODAY);
        votesRepository.save(USER_VOTES_YESTERDAY);
        votesRepository.save(ADMIN_VOTES_TODAY);
        votesRepository.save(ADMIN_VOTES_2DAYS_BEFORE);
        assertThat(votesRepository.getAllVotesForRestaurants(RESTAURANT2.getId(),LocalDate.now().minusDays(1),LocalDate.now())).usingRecursiveComparison().ignoringFields("user.password", "user.registered")
                .ignoringCollectionOrder().isEqualTo(Arrays.asList(USER_VOTES_YESTERDAY, ADMIN_VOTES_2DAYS_BEFORE));
    }
}