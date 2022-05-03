package com.example.restaurantvoting.web.controller.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.to.DishTo;
import com.example.restaurantvoting.to.RestaurantTo;
import com.example.restaurantvoting.util.DishUtil;
import com.example.restaurantvoting.util.RestaurantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.example.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RestaurantRestController {
    static final String REST_URL = "/api/restaurant";

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantTo> get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        RestaurantTo restaurantTo = RestaurantUtil.createTo(repository.getById(id));
        return ResponseEntity.of(Optional.of(restaurantTo));
    }


    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("get all restaurants");
        return RestaurantUtil.getTos(repository.findAll(Sort.by(Sort.Direction.ASC, "name")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        repository.delete(id);
    }

    @GetMapping("/{id}/menu")
    public List<DishTo> getMenu(@PathVariable int id) {
        log.info("get menu for restaurant {}", id);
        return DishUtil.getTos(dishRepository.getMenu(id));
    }
}
