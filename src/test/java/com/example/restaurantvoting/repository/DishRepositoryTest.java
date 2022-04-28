package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.DishTestData;
import com.example.restaurantvoting.model.Dish;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.restaurantvoting.DishTestData.*;
import static com.example.restaurantvoting.RestaurantsTestData.RESTAURANT1;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class DishRepositoryTest extends AbstractTestClass {

    @Autowired
    DishRepository dishRepository;

    @Test
    void save() {
        Dish actual = dishRepository.save(DishTestData.getNew(), RESTAURANT1.getId());
        assertThat(dishRepository.get(actual.getId(), RESTAURANT1.getId())).isNotNull();

    }

    @Test
    void delete() {
        int restaurantId = RESTAURANT1.getId();
        dishRepository.delete(DISH1_RESTAURANT1.getId(), restaurantId);
        assertThat(dishRepository.getMenu(restaurantId)).usingRecursiveComparison().ignoringFields("restaurant.menu")
                .ignoringCollectionOrder().isEqualTo(List.of(DISH2_RESTAURANT1, DISH3_RESTAURANT1));
    }

    @Test
    void get() {
        assertThat(dishRepository.get(DISH1_RESTAURANT1.getId(), DISH1_RESTAURANT1.getRestaurant().getId()))
                .isEqualTo(DishTestData.DISH1_RESTAURANT1);
    }

    @Test
    void getMenu() {
        List<Dish> actualMenuRestaurant1 = dishRepository.getMenu(RESTAURANT1.getId());
        assertThat(actualMenuRestaurant1).usingRecursiveComparison().ignoringFields("restaurant.menu")
                .ignoringCollectionOrder().isEqualTo(MENU_RESTAURANT1);
    }
}