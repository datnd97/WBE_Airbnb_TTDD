package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.model.TypeRoom;

import java.util.Date;
import java.util.Optional;


public interface HomeService {
    Iterable<Home> findAll();

    Optional<Home> findById(Long id);

    Home save(Home home);

    void delete(Long id);

    Iterable<Home> findAllByName(String name);

    //    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where id=#{id}"})
//    Article selectById(int id);
    Iterable<Home> findByBedroom(Integer bedroom);

    Iterable<Home> findByBathroom(Integer bathroom);

    Iterable<Home> findByTypeRoom(TypeRoom typeRoom);

    Iterable<Home> findByTypeHome(TypeHome typeHome);

    Iterable<Home> findByAddress(String address);

    Iterable<Home> findByPrice(Double price);

    Iterable<Home> findByDescription(String description);
}
