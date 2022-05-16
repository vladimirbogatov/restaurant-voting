package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    @Query("select u from User u where lower(u.email) = lower(?1) ")
    Optional<User> findByEmailIgnoreCase(String email);
}
