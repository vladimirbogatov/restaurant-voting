package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface VotesRepository extends BaseRepository<Vote> {

    @Transactional
    @Query("select v from Vote v where v.id = ?1 and v.user.id = ?2")
    Vote getByIdAndUserId(Integer id, Integer user_id);

    @Transactional
    @Query("select v from Vote v where v.user.id=:user_id and v.date=:date")
    Vote getVoteOfUserAtDate(@Param("user_id") int user_id, @Param("date") LocalDate date);

    @Transactional
    @Query("SELECT v from Vote v WHERE v.restaurant.id=:restaurant_id AND v.date >= :startDate AND v.date <= :endDate ORDER BY v.date DESC")
    List<Vote> getAllVotesForRestaurants(@Param("restaurant_id") int restaurants_id, @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);

    @Transactional
    @Query("SELECT v from Vote v WHERE v.user.id=:user_id AND v.date >= :startDate AND v.date <= :endDate ORDER BY v.date DESC")
    List<Vote> getAllVotesOfUser(@Param("user_id") int user_id, @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.user.id=:user_id AND v.restaurant.id=:restaurant_id AND v.date=:date")
    int delete(@Param("user_id") int userId, @Param("restaurant_id") int restaurantId, @Param("date") LocalDate date);
}
