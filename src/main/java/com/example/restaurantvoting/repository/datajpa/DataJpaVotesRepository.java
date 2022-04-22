package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.Votes;
import com.example.restaurantvoting.repository.VotesRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public class DataJpaVotesRepository implements VotesRepository {

    CrudVotesRepository crudVotesRepository;
    CrudRestaurantRepository crudRestaurantRepository;
    CrudUserRepository crudUserRepository;


    @Override
    @Transactional
    public Votes save(int userId, int restaurantId) {
        Votes todayVotes = getActualVotesByUserId(userId, restaurantId, LocalDate.now());
        if (todayVotes != null) {
            todayVotes.setRestaurant(crudRestaurantRepository.getById(restaurantId));
        } else {
            todayVotes = new Votes(crudUserRepository.getById(userId), crudRestaurantRepository.getById(restaurantId));
        }
        return crudVotesRepository.save(todayVotes);
    }

    @Override
    public boolean delete(int restaurantId, int userId) {
        return false;
    }

    @Override
    public Votes getActualVotesByUserId(int userId, int restaurantId, LocalDate date) {
        return crudVotesRepository.getActualVotesByUserId(userId, restaurantId, date);
    }

    @Override
    public List<Votes> getAll(int userId) {
        return null;
    }
}
