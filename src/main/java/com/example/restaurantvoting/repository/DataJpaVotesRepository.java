package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Votes;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaVotesRepository implements VotesRepository {

    CrudVotesRepository crudVotesRepository;
    CrudRestaurantRepository crudRestaurantRepository;
    CrudUserRepository crudUserRepository;

    public DataJpaVotesRepository(CrudVotesRepository crudVotesRepository, CrudRestaurantRepository crudRestaurantRepository,
                                  CrudUserRepository crudUserRepository) {
        this.crudVotesRepository = crudVotesRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

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
    @Transactional
    public boolean delete(int userId, int restaurantId, LocalDate date) {
        return crudVotesRepository.delete(userId, restaurantId, date) != 0;
    }

    @Override
    public Votes getActualVotesByUserId(int userId, int restaurantId, LocalDate date) {
        return crudVotesRepository.getAllVotesOfUser(userId, date, null).stream()
                .filter(votes -> votes.getRestaurant().getId() == restaurantId).findFirst().orElse(null);
    }

    @Override
    public List<Votes> getAllVotesOfUser(int userId, LocalDate startDate, LocalDate endDate) {
        return crudVotesRepository.getAllVotesOfUser(userId, startDate, endDate);
    }

    @Override
    public List<Votes> getAllVotesForRestaurants(int restaurants_id, LocalDate startDate, LocalDate endDate) {
        return crudVotesRepository.getAllVotesForRestaurants(restaurants_id, startDate, endDate);
    }


}
