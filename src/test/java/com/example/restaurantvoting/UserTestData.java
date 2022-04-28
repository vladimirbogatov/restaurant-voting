package com.example.restaurantvoting;

import com.example.restaurantvoting.model.Role;
import com.example.restaurantvoting.model.User;

import java.util.Arrays;
import java.util.List;


public class UserTestData {

    public static final User USER = new User(100020, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(100021, "Admin", "admin@gmail.com", "admin password", Role.ADMIN, Role.USER);
    public static final List<User> USERS = Arrays.asList(USER, ADMIN);

    public static User getNew() {
        return new User(null, "New User", "newUser@mail.ru", "newUserPassword", Role.USER);
    }

}
