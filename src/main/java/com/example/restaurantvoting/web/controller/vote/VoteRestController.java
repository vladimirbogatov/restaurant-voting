package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.error.IllegalRequestDataException;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.repository.UserRepository;
import com.example.restaurantvoting.repository.VotesRepository;
import com.example.restaurantvoting.to.VoteTo;
import com.example.restaurantvoting.util.VotesUtil;
import com.example.restaurantvoting.util.time.DateTimeUtil;
import com.example.restaurantvoting.web.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.restaurantvoting.util.VotesUtil.isTimeBeforeThreshold;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteRestController {

    protected static final String REST_URL = "/api/vote";

    @Autowired
    private VotesRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @Operation(summary = "authorized user get his vote by id")
    public ResponseEntity<VoteTo> get(@PathVariable Integer id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("get vote {}", id);
        VoteTo voteTo = VotesUtil.createTo(repository.getByIdAndUserId(id, authUser.id()));
        return ResponseEntity.of(Optional.of(voteTo));
    }

    @GetMapping
    @Operation(summary = "authorized user get his vote for date")
    public ResponseEntity<VoteTo> getForDate(@AuthenticationPrincipal AuthUser authUser,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get vote for {}", date);
        VoteTo voteTo = VotesUtil.createTo(repository.getVoteOfUserAtDate(authUser.id(), date));
        return ResponseEntity.of(Optional.of(voteTo));
    }

    @PostMapping()
    @Operation(summary = "authorized user create or update vote")
    public ResponseEntity<VoteTo> create(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        log.info("create vote for restaurant {}", restaurantId);
        Vote actual = repository.getVoteOfUserAtDate(authUser.id(), DateTimeUtil.getNowDate());
        if (actual != null) {
            throw new IllegalRequestDataException("User already have voted today, try to update the vote");
        }
        Vote candidateVote = new Vote(null, authUser.getUser(), restaurantRepository.getById(restaurantId), DateTimeUtil.getNowDate());
        Vote saved = repository.save(candidateVote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(VotesUtil.createTo(saved));
    }

    @PutMapping
    public void update(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        log.info("update vote for restaurant {}", restaurantId);
        Vote actual = repository.getVoteOfUserAtDate(authUser.id(), DateTimeUtil.getNowDate());
        if (actual == null) {
            throw new IllegalRequestDataException("User haven't voted today yet, try to create a vote");
        } else {
            if (!isTimeBeforeThreshold()) {
                throw new IllegalRequestDataException("User can't update his vote after threshold time");
            }
        }
        Vote candidateVote = new Vote(null, authUser.getUser(), restaurantRepository.getById(restaurantId), DateTimeUtil.getNowDate());
        candidateVote.setId(actual.getId());
        repository.save(candidateVote);
    }

    @GetMapping("/restaurant/{id}")
    @Operation(summary = "user get count votes for restaurant")
    public long getCountVotesForRestaurant(@PathVariable int id,
                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("get votes for restaurant {} from {} to {}", id, startDate, endDate);
        return repository.getCountVotesForRestaurant(id, DateTimeUtil.atStartOfDayOrMin(startDate), DateTimeUtil.endOfDayOrMax(endDate));
    }

    @GetMapping("/user")
    @Operation(summary = "authorized user get all his votes")
    public List<VoteTo> getAllVotesOfUser(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                          @AuthenticationPrincipal AuthUser authUser) {
        log.info("get votes of user {} from {} to {}", authUser.id(), startDate, endDate);
        return VotesUtil.getTos(repository.getAllVotesOfUser(authUser.id(), DateTimeUtil.atStartOfDayOrMin(startDate), DateTimeUtil.endOfDayOrMax(endDate)));
    }
}
