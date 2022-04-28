package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.RestaurantsTestData;
import com.example.restaurantvoting.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.restaurantvoting.RestaurantsTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class RestaurantRepositoryTest extends AbstractTestClass {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    void save() {
        Restaurant actual = restaurantRepository.save(RestaurantsTestData.getNew());
        assertThat(restaurantRepository.get(actual.getId())).isNotNull();
    }

    @Test
    void delete() {
        restaurantRepository.delete(RESTAURANT1.getId());
        assertThat(restaurantRepository.getAll()).usingRecursiveComparison().ignoringFields("menu")
                .ignoringCollectionOrder().isEqualTo(List.of(RESTAURANT2, RESTAURANT3));
    }

    @Test
    void get() {
        Restaurant actualRestaurant = restaurantRepository.get(RESTAURANT1.getId());
        assertThat(actualRestaurant).isEqualTo(RESTAURANT1);
    }

    @Test
    void getAll() {
        List<Restaurant> actualRestauralList = restaurantRepository.getAll();
        assertThat(actualRestauralList).usingRecursiveComparison().ignoringFields("menu")
                .ignoringCollectionOrder().isEqualTo(RESTAURANTS);
    }
}