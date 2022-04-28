package com.example.restaurantvoting;

import com.example.restaurantvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class RestaurantVotingApplication implements ApplicationRunner {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
