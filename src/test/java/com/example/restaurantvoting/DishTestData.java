package com.example.restaurantvoting;

import com.example.restaurantvoting.model.Dish;

import java.util.List;

public class DishTestData {

    public static final Dish DISH1_RESTAURANT1 = new Dish(100002, RestaurantsTestData.RESTAURANT1, "Морс", 95f);
    public static final Dish DISH2_RESTAURANT1 = new Dish(100003, RestaurantsTestData.RESTAURANT1, "Кулебяка", 500.55f);
    public static final Dish DISH3_RESTAURANT1 = new Dish(100004, RestaurantsTestData.RESTAURANT1, "Борщ", 100f);
    public static final List<Dish> MENU_RESTAURANT1 = List.of(DISH1_RESTAURANT1, DISH2_RESTAURANT1, DISH3_RESTAURANT1);

    public static Dish getNew() {
        return new Dish(null, RestaurantsTestData.RESTAURANT1,"Новое блюдо", 999.99f);
    }
}
