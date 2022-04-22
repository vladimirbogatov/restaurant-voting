package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.RestaurantRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaRetaurantRepository implements RestaurantRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaRetaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int restaurantId) {
        return crudRestaurantRepository.delete(restaurantId) != 0;
    }

    @Override
    public Restaurant get(int restaurantId) {
        return crudRestaurantRepository.findById(restaurantId).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }
}
