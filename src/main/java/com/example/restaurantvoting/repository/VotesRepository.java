package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Votes;

import java.time.LocalDate;
import java.util.List;

public interface VotesRepository {

    // null if not found, when updated
    Votes save(int userId, int restaurantId);

    // false if not found
    boolean delete(int restaurantId, int userId);

    // null if not found
    Votes getActualVotesByUserId(int userId, int restaurantId, LocalDate date);

    List<Votes> getAll(int userId);
}
