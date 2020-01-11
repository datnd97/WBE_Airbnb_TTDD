package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.repository.BookingRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.BookingService;
import com.security.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> listUser() {
        List<User> user = (List<User>) userService.findAll();
        if(user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if(user!=null) {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;
    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @GetMapping("/booking/list-home")
    public ResponseEntity<?> listHomeByUser(){
        Long id = getCurrentUser().getId();
        User user = userService.findById(id);
        ArrayList<Booking> bookings =  bookingRepository.findAllByUser(user);
        ArrayList<Home> homes = new ArrayList<Home>();
        for(int i = 0 ; i < bookings.size(); i++) {
            homes.add(bookings.get(i).getHome());
        }
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }
    @GetMapping("/booking/delete/{id}")
    public ResponseEntity<?> deleteBookingByUser(@PathVariable Long id)
    {
        User user = userService.findById(getCurrentUser().getId());
        Booking booking = bookingRepository.findByUserAndId(user,id);
        bookingService.delete(booking.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
