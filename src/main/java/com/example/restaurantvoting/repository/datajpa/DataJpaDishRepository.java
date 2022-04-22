package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.repository.DishReprository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaDishRepository implements DishReprository {

    CrudDishRepository crudDishRepository;
    CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int dishId, int restaurantId) {
        return crudDishRepository.delete(dishId, restaurantId) != 0;
    }

    @Override
    public Dish get(int dishId, int restaurantId) {
        return crudDishRepository.findById(dishId).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public List<Dish> getMenu(int restaurantId) {
        return crudDishRepository.getMenu(restaurantId);
    }
}
