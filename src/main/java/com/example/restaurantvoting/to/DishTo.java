package com.example.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends BaseTo {

    String name;

    float price;

    public DishTo(Integer id, String name, float price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
