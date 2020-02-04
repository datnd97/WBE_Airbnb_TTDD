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
    List<Home> findAllByUserId(Long id);
    List<Home> findAllByIsBookingAndUserId(Boolean status,Long id);
    Home findByBookingId(Long id);
    List<Home> findAllByIsCheckin(Boolean isCheckIn);
    List<Home> findAllByBooking(List<Booking> bookings);
    List<Home> findAllByIsCheckinAndBooking(Boolean isCheckIn,List<Booking> bookings);
}
