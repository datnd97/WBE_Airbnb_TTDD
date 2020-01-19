package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home,Long>, JpaSpecificationExecutor<Home> {
    Iterable<Home> findAllByName(String name);
    Home findByBooking(Booking booking);
    List<Home> findAllByPriceBetween(Double min,Double max);
}
