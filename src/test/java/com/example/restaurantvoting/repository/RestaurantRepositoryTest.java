package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.AbstractTestClass;
import com.example.restaurantvoting.RestaurantsTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.restaurantvoting.RestaurantsTestData.RESTAURANT1;

@RunWith(SpringRunner.class)
@SpringBootTest
class RestaurantRepositoryTest extends AbstractTestClass {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
        Assertions.assertSame(RestaurantsTestData.RESTAURANT1, restaurantRepository.get(RESTAURANT1.getId()) );
    }

    @Test
    void getAll() {
    }
}