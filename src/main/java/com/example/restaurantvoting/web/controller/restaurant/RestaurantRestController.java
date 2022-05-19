package com.example.restaurantvoting.web.controller.restaurant;

import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.to.DishTo;
import com.example.restaurantvoting.to.RestaurantTo;
import com.example.restaurantvoting.util.DishUtil;
import com.example.restaurantvoting.util.RestaurantUtil;
import com.example.restaurantvoting.util.time.DateTimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/{id}/menus")
    @Operation(summary = "user get menues of restaurants (id) which actual from date to date")
    public List<DishTo> getMenu(@PathVariable int id,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("get menu for restaurant {} which actual from {} to {}", id, startDate, endDate);
        return DishUtil.getTos(dishRepository.getMenu(id, DateTimeUtil.atStartOfDayOrMin(startDate), DateTimeUtil.endOfDayOrMax(endDate)));
    }
}
