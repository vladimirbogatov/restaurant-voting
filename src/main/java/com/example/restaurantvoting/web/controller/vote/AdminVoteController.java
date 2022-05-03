package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.repository.VotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminVoteController {

    protected static final String REST_URL = "/api/admin/vote";

    @Autowired
    VotesRepository repository;

    @DeleteMapping("/user/{userId}/restaurant/{restaurantId}")
    public void delete(@PathVariable int userId, @PathVariable int restaurantId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("delete vote of user {} for restaurant {} at {}", userId, restaurantId, date);
        repository.delete(userId, restaurantId, date);
    }

}
