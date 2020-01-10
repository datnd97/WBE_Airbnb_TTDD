package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Home;

import java.util.Date;
import java.util.Optional;


public interface HomeService {
    Iterable<Home> findAll();
    Home findById(Long id);
    Home save(Home home);
    void delete(Long id);
    Iterable<Home> findAllByName(String name);

    //    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where id=#{id}"})
//    Article selectById(int id);

}
