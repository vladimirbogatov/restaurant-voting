package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.restaurantvoting.UserTestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest extends AbstractTestClass {

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        User actual = userRepository.save(getNew());
        assertThat(actual).isNotNull();
    }

    @Test
    void delete() {
        userRepository.delete(USER.getId());
        assertThat(userRepository.findAll()).usingRecursiveComparison().ignoringFields("password","registered")
                .ignoringCollectionOrder().isEqualTo(List.of(ADMIN));
    }

    @Test
    void get() {
        User actual = userRepository.getById(USER.getId());
        assertThat(actual).isEqualTo(USER);
    }

    @Test
    void getAll() {
        List<User> actualUserList = userRepository.findAll();
        assertThat(actualUserList).usingRecursiveComparison().ignoringFields("password","registered")
                .ignoringCollectionOrder().isEqualTo(USERS);
    }
}