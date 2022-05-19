package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.User;
import com.example.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VotesRepository extends BaseRepository<Vote> {

    @Query("select v from Vote v where v.id = ?1 and v.user.id = ?2")
    Vote getByIdAndUserId(Integer id, Integer user_id);

    @Query("select v from Vote v where v.user.id=:user_id and v.date=:date")
    Vote getVoteOfUserAtDate(@Param("user_id") int user_id, @Param("date") LocalDate date);

    @Query("SELECT count(v) from Vote v WHERE v.restaurant.id=:restaurant_id AND v.date >= :startDate AND v.date <= :endDate")
    long getCountVotesForRestaurant(@Param("restaurant_id") int restaurants_id, @Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);

    @Query("SELECT v from Vote v WHERE v.user.id=:user_id AND v.date >= :startDate AND v.date <= :endDate ORDER BY v.date DESC")
    List<Vote> getAllVotesOfUser(@Param("user_id") int user_id, @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate);

    @Query("select v from Vote v where v.user = ?1 and v.date = ?2")
    Vote getVoteByUserAndDate(@NotNull User user, LocalDate date);
}
