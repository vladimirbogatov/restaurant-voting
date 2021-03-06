package com.example.restaurantvoting.web.controller.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.web.controller.MatcherFactory;
import com.example.restaurantvoting.web.controller.restaurant.RestaurantsTestData;

import java.time.LocalDate;
import java.util.List;

public class DishTestData {

    public static final int DISH_ID = 1;

    public static final Dish DISH1_RESTAURANT1 = new Dish(DISH_ID, RestaurantsTestData.RESTAURANT1, "Морс", 95_00, LocalDate.of(2022, 4, 30));
    public static final Dish DISH2_RESTAURANT1 = new Dish(DISH_ID + 1, RestaurantsTestData.RESTAURANT1, "Кулебяка", 500_55, LocalDate.of(2022, 4, 30));
    public static final Dish DISH3_RESTAURANT1 = new Dish(DISH_ID + 2, RestaurantsTestData.RESTAURANT1, "Борщ", 100_00, LocalDate.of(2022, 4, 30));
    public static final Dish DISH1_RESTAURANT1_NEW = new Dish(DISH_ID + 3, RestaurantsTestData.RESTAURANT1, "Морс new", 100_00, LocalDate.of(2022, 5, 1));
    public static final Dish DISH2_RESTAURANT1_NEW = new Dish(DISH_ID + 4, RestaurantsTestData.RESTAURANT1, "Кулебяка new", 505_55, LocalDate.of(2022, 5, 1));
    public static final Dish DISH3_RESTAURANT1_NEW = new Dish(DISH_ID + 5, RestaurantsTestData.RESTAURANT1, "Борщ new", 105_00, LocalDate.of(2022, 5, 1));
    public static final List<Dish> MENU_RESTAURANT1 = List.of(DISH1_RESTAURANT1, DISH3_RESTAURANT1, DISH2_RESTAURANT1);//ORDER BY d.price ASC
    public static final List<Dish> MENU_RESTAURANT1_NEW = List.of(DISH1_RESTAURANT1_NEW, DISH3_RESTAURANT1_NEW, DISH2_RESTAURANT1_NEW);//ORDER BY d.price ASC
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant", "created");

    public static Dish getNew() {
        return new Dish(null, RestaurantsTestData.RESTAURANT1, "Новое блюдо", 999_99, LocalDate.of(2022, 5, 2));
    }

    public static Dish getUpdated() {
        return new Dish(DISH_ID, RestaurantsTestData.RESTAURANT1, "Морс обновлённый", 100, LocalDate.of(2022, 4, 30));
    }
}
