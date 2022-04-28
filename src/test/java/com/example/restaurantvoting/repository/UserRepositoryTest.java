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
class UserRepositoryTest extends AbstractTestClass{

    @Autowired
    UserReprository userReprository;

    @Test
    void save() {
        User actual = userReprository.save(getNew());
        System.out.println(actual);
        assertThat(actual).isNotNull();
    }

    @Test
    void delete() {
        userReprository.delete(USER.getId());
        assertThat(userReprository.getAll()).usingRecursiveComparison().ignoringFields("password","registered")
                .ignoringCollectionOrder().isEqualTo(List.of(ADMIN));
    }

    @Test
    void get() {
        User actual = userReprository.get(USER.getId());
        assertThat(actual).isEqualTo(USER);
    }

    @Test
    void getAll() {
        List<User> actualUserList = userReprository.getAll();
        assertThat(actualUserList).usingRecursiveComparison().ignoringFields("password","registered")
                .ignoringCollectionOrder().isEqualTo(USERS);
    }
}