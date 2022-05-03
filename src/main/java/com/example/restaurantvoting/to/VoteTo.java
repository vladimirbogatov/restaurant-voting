package com.example.restaurantvoting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {

    private int restaurant_id;

    private LocalDate date;

    public VoteTo(Integer id, int restaurant_id, LocalDate date) {
        super(id);
        this.restaurant_id = restaurant_id;
        this.date = date;
    }
}
