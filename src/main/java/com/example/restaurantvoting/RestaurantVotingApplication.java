package com.example.restaurantvoting;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.datajpa.CrudRestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class RestaurantVotingApplication implements ApplicationRunner {

    private final CrudRestaurantRepository crudRestaurantRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
/*        crudRestaurantRepository.save(new Restaurant("Прага"));
        crudRestaurantRepository.save(new Restaurant("Прага"));
        crudRestaurantRepository.findAll().forEach(restaurant -> System.out.println(restaurant.getName()));*/
    }
}
