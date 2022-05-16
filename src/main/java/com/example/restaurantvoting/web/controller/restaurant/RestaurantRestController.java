package com.example.restaurantvoting.web.controller.restaurant;

import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.to.DishTo;
import com.example.restaurantvoting.to.RestaurantTo;
import com.example.restaurantvoting.util.DishUtil;
import com.example.restaurantvoting.util.RestaurantUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantRestController {
    protected static final String REST_URL = "/api/restaurant";

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/{id}")
    @Operation(summary = "authorized user get restaurant by id")
    public ResponseEntity<RestaurantTo> get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        RestaurantTo restaurantTo = RestaurantUtil.createTo(repository.getById(id));
        return ResponseEntity.of(Optional.of(restaurantTo));
    }


    @GetMapping
    @Operation(summary = "authorized user get all restaurants")
    public List<RestaurantTo> getAll() {
        log.info("get all restaurants");
        return RestaurantUtil.getTos(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    @GetMapping("/{id}/menu")
    @Operation(summary = "authorized user get menu of restaurants (id)")
    public List<DishTo> getMenu(@PathVariable int id) {
        log.info("get menu for restaurant {}", id);
        return DishUtil.getTos(dishRepository.getMenu(id));
    }
}
