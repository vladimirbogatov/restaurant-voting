package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:user_id")
    int delete(@Param("user_id") int userId);
}
