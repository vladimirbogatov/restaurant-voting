package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Votes;

import java.time.LocalDate;
import java.util.List;

public interface VotesRepository {

    // null if not found, when updated
    Votes save(int userId, int restaurantId);

    // false if not found
    boolean delete(int userId, int restaurantId, LocalDate date);

    // null if not found
    Votes getActualVotesByUserId(int userId, int restaurantId, LocalDate date);

    List<Votes> getAllVotesOfUser(int userId, LocalDate startDate, LocalDate endDate);

    List<Votes> getAllVotesForRestaurants(int restaurants_id, LocalDate startDate, LocalDate endDate);
}
