package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:dish_id AND d.restaurant.id=:restaurant_id")
    int delete(@Param("dish_id") int dishId, @Param("restaurant_id") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurant_id ORDER BY d.price ASC  ")
    List<Dish> getMenu(@Param("restaurant_id") int restaurantId);
}
