package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository {

    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int restaurantId);

    // null if not found
    Restaurant get(int restaurantId);

    List<Restaurant> getAll();
}
