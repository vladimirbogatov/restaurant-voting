package com.example.restaurantvoting;

import com.example.restaurantvoting.model.Restaurant;

import java.util.List;

import static com.example.restaurantvoting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantsTestData {

    public static final int RESTAURANT_ID = START_SEQ + 5;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID, "Прага");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID + 1, "Рыба&Мясо");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID + 2, "Ocean Basket");
    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан");
    }
}

