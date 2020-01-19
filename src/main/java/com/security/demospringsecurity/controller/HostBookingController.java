package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.repository.BookingRepository;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.BookingService;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.UserService;
import com.security.demospringsecurity.util.DateToMilisecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/host/booking")
public class HostBookingController {
    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HomeRepository homeRepository;

    @GetMapping
    public ResponseEntity<?> listBooking() {
        System.out.println("Da vao day");
        List<Booking> bookings = (List<Booking>) bookingService.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHost(@PathVariable Long id) throws ParseException {
        Optional<Booking> booking = bookingService.findById(id);
        booking.get().setCancelled(Boolean.TRUE);
        if (booking != null) {
            bookingService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list-booking-host")
    public ResponseEntity<?> listBookingByHost() {
        List<Booking> result = new ArrayList<>();
        Long userId = getCurrentUser().getId();
        List<Home> homes = homeRepository.findAllByIsBookingAndUserId(Boolean.TRUE,userId);
        for (Home home: homes) {
            Booking booking = bookingRepository.findBookingByHome(home);
            result.add(booking);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
