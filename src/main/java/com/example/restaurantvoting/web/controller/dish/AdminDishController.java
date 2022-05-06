package com.example.restaurantvoting.web.controller.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.to.DishTo;
import com.example.restaurantvoting.util.DishUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.example.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminDishController {

    protected static final String REST_URL = "/api/admin/dish";

    @Autowired
    private DishRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant/{id}")
    @Operation(summary = "admin get dish by id")
    public ResponseEntity<DishTo> create(@RequestBody @Valid Dish dish, @PathVariable int id) {
        log.info("create {}", dish);
        checkNew(dish);
        dish.setRestaurant(restaurantRepository.getById(id));
        Dish saved = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(DishUtil.createTo(saved));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "admin delete dish")
    public void delete(@PathVariable int id) {
        log.info("delete dish {}", id);
        repository.delete(id);
    }

    @PatchMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "admin update dish")
    public void update(@RequestBody @Valid Dish dish, @PathVariable int id) {
        log.info("update {}", dish);
        assureIdConsistent(dish.getRestaurant(), id);
        dish.setRestaurant(restaurantRepository.getById(id));
        repository.save(dish);
    }
}
