package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id ORDER BY d.price ASC  ")
    List<Dish> getMenu(@Param("restaurant_id") int restaurantId);
}
