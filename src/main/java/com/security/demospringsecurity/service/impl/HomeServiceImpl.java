package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    public Home findById(Long id){
       return homeRepository.findById(id).get();
    }

    @Override
    public Home save(Home home) {
        return homeRepository.save(home);
    }

    @Override
    public void delete(Long id) {
        homeRepository.deleteById(id);
    }

    @Override
    public Iterable<Home> findAllByName(String name) {
        return homeRepository.findAllByName(name);
    }

    @Override
    public List<Home> findAllByUserId(Long id) {
        return homeRepository.findAllByUserId(id);
    }


}
