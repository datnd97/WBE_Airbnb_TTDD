package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.login.User;
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
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
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
    @RequestMapping(value = "create/{id}",method = RequestMethod.POST
            ,produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createBooking( @PathVariable("id") Long id, @RequestBody Booking booking) {
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
    @GetMapping
    public ResponseEntity<?> listBooking(){

        return new ResponseEntity<>(bookingService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
       Optional<Booking> booking = bookingService.findById(id);
        if( booking != null) {
            return new ResponseEntity<>( booking,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.findById(id);
        booking.get().setCancelled(Boolean.TRUE);
        if(booking!= null) {
            bookingService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
