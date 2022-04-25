package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface CrudVotesRepository extends JpaRepository<Votes, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Votes v WHERE v.user.id=:user_id AND v.restaurant.id=:restaurant_id AND v.date=:date")
    int delete(@Param("user_id") int userId, @Param("restaurant_id") int restaurantId, @Param("date") LocalDate date);

    @Transactional
    @Query("SELECT v from Votes v WHERE v.restaurant.id=:restaurant_id AND v.date >= :startDate AND v.date < :endDate")
    List<Votes> getAllVotesForRestaurants(@Param("restaurant_id") int restaurants_id, @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);

    @Transactional
    @Query("SELECT v from Votes v WHERE v.user.id=:user_id AND v.date >= :startDate AND v.date < :endDate")
    List<Votes> getAllVotesOfUser(@Param("user_id") int user_id, @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);

}
