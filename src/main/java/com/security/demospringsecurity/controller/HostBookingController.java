package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.repository.BookingRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.BookingService;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping
    public ResponseEntity<?> listBooking() {
        System.out.println("Da vao day");
        List<Booking> bookings = (List<Booking>) bookingService.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        booking.get().setCancelled(Boolean.TRUE);
        if (booking != null) {
            bookingService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list-booking-host")
    public ResponseEntity<?> listBookingByHost() {
        Long userId = getCurrentUser().getId();
        List<Booking> bookingList = this.bookingRepository.findBookingByCancelledAndUserId(Boolean.FALSE, userId);
        if (bookingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }
}
