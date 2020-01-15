package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.BookingService;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/booking")
public class UserBookingController {
    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @Autowired
    private BookingService bookingService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private UserService userService;

    @GetMapping("/list-booking-user")
    public ResponseEntity<?> listBookingByUser() {
        Long userId = getCurrentUser().getId();
        List<Booking> bookingList = this.bookingService.findBookingByUserId(userId);
        if(bookingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookingList,HttpStatus.OK);
    }

    @RequestMapping(value = "create/{id}",method = RequestMethod.POST
            ,produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createBooking(@PathVariable("id") Long id, @RequestBody Booking booking) {
        Home home = homeService.findById(id);
        booking.setCancelled(Boolean.FALSE);
        booking.setHome(home);
        Date now = new Date();
        booking.setTimeNow(now);
        User user = userService.findById(getCurrentUser().getId());
        booking.setUser(user);
        bookingService.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
}