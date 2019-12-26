package com.service;

import com.model.Home;

import java.util.Optional;

public interface HomeService {
    Iterable<Home> findAll();
    Optional<Home> findById(Long id);
    Home save(Home home);
    void delete(Long id);
}
