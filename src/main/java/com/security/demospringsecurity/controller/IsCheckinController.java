package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.request.CheckInForm;
import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.repository.BookingRepository;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.BookingService;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class IsCheckinController {
    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

    @Autowired
    private HomeService homeService;

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @PutMapping("/check-in")
    public ResponseEntity<?> updateCheckIn(@RequestBody CheckInForm checkInForm) {
        Home home = homeService.findById(checkInForm.getHomeId());
        home.setIsCheckin(checkInForm.getIsCheckin());
        homeService.save(home);
        return new ResponseEntity<>(home.getIsCheckin(), HttpStatus.CREATED);
    }
    @GetMapping("/list-home-checkin")
    public ResponseEntity<?> listHomeCheckIn() {
        List<Booking> bookingList = this.bookingService.findBookingByUserId(getCurrentUser().getId());
        List<Home> homes = homeRepository.findAllByIsCheckinAndBooking(Boolean.TRUE,bookingList);
        if (homes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }
}
