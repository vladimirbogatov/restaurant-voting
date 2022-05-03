package com.example.restaurantvoting.util;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.to.DishTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class DishUtil {
    public static DishTo createTo(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream().map(DishUtil::createTo).toList();
    }
}
