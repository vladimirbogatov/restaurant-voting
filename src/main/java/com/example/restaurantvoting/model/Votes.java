package com.example.restaurantvoting.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "name_unique_votes_idx")})
public class Votes extends AbstractBaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;
    
    private LocalDate date;

    public Votes(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public Votes(User user, Restaurant restaurant, LocalDate date) {
        this(user, restaurant);
        this.date = date;
    }

    @Override
    public String toString() {
        return "Votes{" +
                "user=" + user +
                ", restaurant=" + restaurant +
                ", date=" + date +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Votes votes = (Votes) o;
        return Objects.equals(user, votes.user) && Objects.equals(restaurant, votes.restaurant) && Objects.equals(date, votes.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, restaurant, date);
    }
}
