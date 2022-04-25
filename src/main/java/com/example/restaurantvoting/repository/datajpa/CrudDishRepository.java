package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.repository.DishReprository;
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

    @Transactional(readOnly = true)
    class DataJpaDishRepository implements DishReprository {

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
}
