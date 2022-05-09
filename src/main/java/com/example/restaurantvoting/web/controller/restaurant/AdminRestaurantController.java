package com.example.restaurantvoting.web.controller.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.to.RestaurantTo;
import com.example.restaurantvoting.util.RestaurantUtil;
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
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminRestaurantController {

    static final String REST_URL = "/api/admin/restaurant";

    @Autowired
    private RestaurantRepository repository;

    @PostMapping
    @Operation(summary = "admin create restaurant")
    public ResponseEntity<RestaurantTo> create(@RequestBody @Valid Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant saved = repository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(RestaurantUtil.createTo(saved));
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "admin update restaurant")
    public void update(@RequestBody @Valid Restaurant restaurant, @PathVariable int id) {
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "admin delete restaurant")
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        repository.delete(id);
    }
}
