package com.example.restaurantvoting.repository.datajpa;

import com.example.restaurantvoting.model.User;
import com.example.restaurantvoting.repository.UserReprository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaUserRepository implements UserReprository {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    CrudUserRepository crudUserRepository;

    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public boolean delete(int userId) {
        return crudUserRepository.delete(userId) != 0;
    }

    @Override
    public User get(int userId) {
        return crudUserRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }
}
