package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BaseRepository<User> {

    @Query("select u from User u where u.id = ?1 order by u.name")
    User getUserByIdOrderByNameAsc();
}
