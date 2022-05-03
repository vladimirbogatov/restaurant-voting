package com.example.restaurantvoting.web.controller.vote;

import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.repository.UserRepository;
import com.example.restaurantvoting.repository.VotesRepository;
import com.example.restaurantvoting.to.VoteTo;
import com.example.restaurantvoting.util.VotesUtil;
import com.example.restaurantvoting.web.AuthUser;
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

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteRestController {

    protected static final String REST_URL = "/api/vote";


    @Autowired
    public VotesRepository repository;

    @Autowired
    public RestaurantRepository restaurantRepository;

    @Autowired
    public UserRepository userRepository;

    // todo set auther user
    @GetMapping("/{id}")
    public ResponseEntity<VoteTo> get(@PathVariable Integer id, @AuthenticationPrincipal AuthUser authUser) {
        log.info("get vote {}", id);
        VoteTo voteTo = VotesUtil.createTo(repository.getByIdAndUserId(id, authUser.id()));
        return ResponseEntity.of(Optional.of(voteTo));
    }

    @PostMapping
    public ResponseEntity<VoteTo> createOrUpdate(@RequestParam int restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        log.info("create vote for restaurant {}", restaurantId);
        //todo Не забыть заменить пользователя
        Vote candidateVote = new Vote(authUser.getUser(), restaurantRepository.getById(restaurantId), VotesUtil.getNowDate());
        Vote actual = repository.getVoteOfUserAtDate(authUser.id(), VotesUtil.getNowDate());
        Vote saved = repository.save(VotesUtil.candidatePrepare(candidateVote, actual));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(VotesUtil.createTo(saved));
    }

    @GetMapping("/restaurant/{id}")
    public List<VoteTo> getAllVotesForRestaurant(@PathVariable int id,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("get votes for restaurant {} from {} to {}", id, startDate, endDate);
        return VotesUtil.getTos(repository.getAllVotesForRestaurants(id, VotesUtil.atStartOfDayOrMin(startDate), VotesUtil.endOfDayOrMax(endDate)));
    }


    @GetMapping("/user")
    public List<VoteTo> getAllVotesOfUser(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                          @AuthenticationPrincipal AuthUser authUser) {
        log.info("get votes of user {} from {} to {}", authUser.id(), startDate, endDate);
        return VotesUtil.getTos(repository.getAllVotesOfUser(authUser.id(), VotesUtil.atStartOfDayOrMin(startDate), VotesUtil.endOfDayOrMax(endDate)));
    }
}
