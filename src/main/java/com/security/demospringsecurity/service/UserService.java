package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(Long id);
}
