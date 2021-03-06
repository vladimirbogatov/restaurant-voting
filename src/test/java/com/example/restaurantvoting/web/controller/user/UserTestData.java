package com.example.restaurantvoting.web.controller.user;

import com.example.restaurantvoting.model.Role;
import com.example.restaurantvoting.model.User;
import com.example.restaurantvoting.web.controller.MatcherFactory;

import java.util.Arrays;
import java.util.List;


public class UserTestData {

    public static final int USER_ID = 1;

    public static final String USER_EMAIL = "user@yandex.ru";
    public static final String ADMIN_EMAIL = "admin@gmail.com";
    public static final User USER = new User(USER_ID, "User", USER_EMAIL, "password", Role.USER);
    public static final User ADMIN = new User(USER_ID + 1, "Admin", ADMIN_EMAIL, "admin password", Role.ADMIN, Role.USER);
    public static final List<User> USERS = Arrays.asList(ADMIN, USER);//Sort.Direction.ASC, "name", "email"

    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingEqualsComparator(User.class);

    public static User getNew() {
        return new User(null, "New User", "newUser@mail.ru", "newUserPassword", Role.USER);
    }

    public static User getUpdated() {
        return new User(USER_ID, "User Updated", USER_EMAIL, "password", Role.USER);
    }
}
