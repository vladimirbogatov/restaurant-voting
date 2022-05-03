package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    @Query("select u from User u where lower(u.email) = lower(?1) ")
    Optional<User> findByEmailIgnoreCase(String email);
}
