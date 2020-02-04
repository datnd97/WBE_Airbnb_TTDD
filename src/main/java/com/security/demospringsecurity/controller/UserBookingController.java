package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.response.ResponseMessage;
import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.repository.BookingRepository;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.BookingService;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.UserService;
import com.security.demospringsecurity.util.BooleanDate;
import com.security.demospringsecurity.util.DateToMilisecond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

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

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private BookingRepository bookingRepository;


    @GetMapping("/list-booking-user")
    public ResponseEntity<?> listBookingByUser() {
        Long userId = getCurrentUser().getId();
        List<Booking> bookingList = this.bookingService.findBookingByUserId(userId);
        if(bookingList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookingList,HttpStatus.OK);
    }

    @RequestMapping(value = "create/{id}",method = RequestMethod.POST
            ,produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createBooking(@PathVariable("id") Long id, @RequestBody Booking booking) throws ParseException {
        boolean status = BooleanDate.afterBefore(booking.getCheckin(),booking.getCheckout());
        if(!status) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Home home = homeService.findById(id);
        home.setIsBooking(Boolean.TRUE);
        booking.setCancelled(Boolean.FALSE);
        booking.setHome(home);
        Date now = new Date();
        booking.setTimeNow(now);
        User user = userService.findById(getCurrentUser().getId());
        booking.setUser(user);
        bookingService.save(booking);
        homeService.save(home);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> cancelBookingUser(@PathVariable Long id) throws ParseException {
        Optional<Booking> booking = bookingService.findById(id);
        String checkin = booking.get().getCheckin();
        String checkout = booking.get().getCheckout();
        int totalDays = DateToMilisecond.totalDay(checkin,checkout);
        if ( booking != null && totalDays!= 1) {
            if(totalDays == 0){
                return new ResponseEntity<ResponseMessage>(
                        new ResponseMessage(false, "Cannot cancel the booking", null),
                        HttpStatus.OK);            }
            booking.get().setCancelled(Boolean.TRUE);
            booking.get().getHome().setIsBooking(Boolean.FALSE);
            bookingService.save(booking.get());
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(true, "Confirm booking cancel", null),
                    HttpStatus.OK);
        }
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(false, "Cannot cancel the booking", null),
                HttpStatus.OK);
    }


}
