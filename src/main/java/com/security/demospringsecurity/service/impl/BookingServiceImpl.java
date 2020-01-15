package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.repository.BookingRepository;
import com.security.demospringsecurity.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll() ;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findBookingByUserId(Long id) {
        return bookingRepository.findBookingByUserId(id);
    }


}
