package com.example.restaurantvoting.web.controller.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.web.controller.MatcherFactory;

import java.util.List;

import static com.example.restaurantvoting.web.controller.user.UserTestData.USER_ID;

public class RestaurantsTestData {

    public static final int RESTAURANT_ID = 1;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID, "Прага");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID + 1, "Рыба&Мясо");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID + 2, "Ocean Basket");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT3, RESTAURANT1, RESTAURANT2); //Sort.by(Sort.Direction.ASC, "name")

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");

    public static Restaurant getNew() {
        return new Restaurant(null, "Новый ресторан");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "Прага новая");
    }
}

