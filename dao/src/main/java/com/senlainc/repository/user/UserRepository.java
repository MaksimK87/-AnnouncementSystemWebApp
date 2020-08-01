package com.senlainc.repository.user;

import com.senlainc.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User findByUsername(String username);

    Optional<User> findById(Long userId);

    List<User> findAll();

    long save(User user);

    void update(User user);

    void deleteById(Long id);
}
