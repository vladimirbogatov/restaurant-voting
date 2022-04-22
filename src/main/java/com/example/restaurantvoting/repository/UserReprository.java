package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.User;

import java.util.List;

public interface UserReprository {

    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int userId);

    // null if not found
    User get(int userId);

    List<User> getAll();
}
