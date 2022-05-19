package com.example.restaurantvoting.util;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.to.DishTo;
import com.example.restaurantvoting.util.time.DateTimeUtil;
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

    public static Dish createNewFromTo(DishTo dishTo) {
        return new Dish(null, null, dishTo.getName(), dishTo.getPrice(), DateTimeUtil.getNowDate());
    }
}
