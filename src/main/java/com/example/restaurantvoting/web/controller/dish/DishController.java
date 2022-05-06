package com.example.restaurantvoting.web.controller.dish;

import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.to.DishTo;
import com.example.restaurantvoting.util.DishUtil;
import com.example.restaurantvoting.web.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class DishController {

    protected static final String REST_URL = "/api/dish";

    @Autowired
    private DishRepository repository;

    @GetMapping("/{id}")
    @Operation(summary = "authorized user get dish by id")
    public ResponseEntity<DishTo> get(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("get dish {}", id);
        DishTo dishTo = DishUtil.createTo(repository.getById(id));
        return ResponseEntity.of(Optional.of(dishTo));
    }
}
