package com.example.restaurantvoting.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
public class Restaurant extends AbstractBaseEntity {

    @Size(min = 1, max = 128)
    @Column(nullable = false, unique = true)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> menu;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Restaurant)) {
            return false;
        }

        Restaurant restaurant = (Restaurant) o;

        return name.equals(restaurant.name);
    }

}
