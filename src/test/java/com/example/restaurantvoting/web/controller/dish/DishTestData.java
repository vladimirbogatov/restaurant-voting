package com.example.restaurantvoting.web.controller.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.web.controller.MatcherFactory;
import com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData;

import java.util.List;

public class DishTestData {

    public static final Dish DISH1_RESTAURANT1 = new Dish(1, RestaurantsTestData.RESTAURANT1, "Морс", 95f);
    public static final Dish DISH2_RESTAURANT1 = new Dish(2, RestaurantsTestData.RESTAURANT1, "Кулебяка", 500.55f);
    public static final Dish DISH3_RESTAURANT1 = new Dish(3, RestaurantsTestData.RESTAURANT1, "Борщ", 100f);
    public static final List<Dish> MENU_RESTAURANT1 = List.of(DISH1_RESTAURANT1, DISH3_RESTAURANT1, DISH2_RESTAURANT1);//ORDER BY d.price ASC
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static Dish getNew() {
        return new Dish(null, RestaurantsTestData.RESTAURANT1,"Новое блюдо", 999.99f);
    }
}
