package com.example.restaurantvoting.web;

import com.example.restaurantvoting.model.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
// todo убрать. Это для тестов
    public static int getAuthUser() {
        return 2;
    }
}