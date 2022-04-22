package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishReprository {

    // null if not found, when updated
    Dish save(Dish dish, int restaurantId);

    // false if not found
    boolean delete(int dishId, int restaurantId);

    // null if not found
    Dish get(int dishId, int restaurantId);

    List<Dish> getMenu(int restaurantId);
}
