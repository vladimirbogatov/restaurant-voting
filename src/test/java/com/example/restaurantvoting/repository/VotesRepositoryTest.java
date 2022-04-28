package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Votes;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class VotesRepositoryTest extends AbstractTestClass {

    @Autowired
    VotesRepository votesRepository;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void getActualVotesByUserId() {
    }

    @Test
    void getAllVotesOfUser() {
    }

    @Test
    void getAllVotesForRestaurants() {
    }
}