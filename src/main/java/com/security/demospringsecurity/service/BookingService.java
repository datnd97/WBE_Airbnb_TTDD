package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Iterable<Booking> findAll();
    Optional<Booking> findById(Long id);
    Booking save(Booking booking);
    void delete(Long id);
}
