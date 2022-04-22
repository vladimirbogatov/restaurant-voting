package com.example.restaurantvoting.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restuarant_id"}, name = "name_unique_restaurant_idx")})
public class Dish extends AbstractBaseEntity {

    @Size(max = 128)
    @NotNull
    private String name;

    @NotNull
    @Column(scale = 2)
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restuarant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    // TODO: 14.04.2022 del
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // TODO: 12.04.2022 проверить JacksonBack reference
//    @JsonBackReference
    private Restaurant restaurant;*/

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    // TODO: 14.04.2022 del
/*    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }*/



}
