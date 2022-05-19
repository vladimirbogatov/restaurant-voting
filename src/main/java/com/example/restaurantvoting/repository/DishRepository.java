package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id AND d.created >= :startDate AND d.created <= :endDate ORDER BY d.created DESC, d.price ASC")
    List<Dish> getMenu(@Param("restaurant_id") int restaurantId, @Param("startDate") LocalDate startDate,
                       @Param("endDate") LocalDate endDate);
}
