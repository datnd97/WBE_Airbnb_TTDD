package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.model.TypeRoom;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Iterable<Home> findAllByName(String name) {
        return homeRepository.findAllByName(name);
    }

    @Override
    public Iterable<Home> findByBedroom(Integer bedroom) {
        return homeRepository.findByBedroom(bedroom);
    }

    @Override
    public Iterable<Home> findByBathroom(Integer bathroom) {
        return homeRepository.findByBathroom(bathroom);
    }

    @Override
    public Iterable<Home> findByTypeRoom(TypeRoom typeRoom) {
        return homeRepository.findByTypeRoom(typeRoom);
    }

    @Override
    public Iterable<Home> findByTypeHome(TypeHome typeHome) {
        return homeRepository.findByTypeHome(typeHome);
    }

    @Override
    public Iterable<Home> findByAddress(String address) {
        return homeRepository.findByAddress(address);
    }

    @Override
    public Iterable<Home> findByPrice(Double price) {
        return homeRepository.findByPrice(price);
    }

    @Override
    public Iterable<Home> findByDescription(String description) {
        return homeRepository.findByDescription(description);
    }




}
