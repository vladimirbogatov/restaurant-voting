package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface CrudVotesRepository extends JpaRepository<Votes, Integer> {

    @Query("SELECT v FROM Votes v WHERE v.user.id=:user_id AND v.restaurant.id=:restaurant_id AND v.date=:date")
    Votes getActualVotesByUserId(int userId, int restaurantId, LocalDate date);
}
