package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Home;

import java.util.Optional;

public interface HomeService {
    Iterable<Home> findAll();
    Optional<Home> findById(Long id);
    Home save(Home home);
    void delete(Long id);
    Iterable<Home> findAllByName(String name);
}
