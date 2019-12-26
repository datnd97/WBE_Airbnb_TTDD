package com.service.impl;

import com.model.Home;
import com.repository.HomeRepository;
import com.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeRepository homeRepository;
    @Override
    public Iterable<Home> findAll() {
        return homeRepository.findAll();
    }

    @Override
    public Optional<Home> findById(Long id) {
        return homeRepository.findById(id);
    }

    @Override
    public Home save(Home home) {
        return homeRepository.save(home);
    }

    @Override
    public void delete(Long id) {
        homeRepository.deleteById(id);
    }
}
