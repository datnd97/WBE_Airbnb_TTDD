package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking) {
        bookingService.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> listHome() {
        List<Booking> bookings = (List<Booking>) bookingService.findAll();
        if(bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }
}
