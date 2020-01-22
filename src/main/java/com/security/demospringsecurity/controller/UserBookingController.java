package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.response.ResponseMessage;
import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.Result;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.BookingService;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.UserService;
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
    public ResponseEntity<?> deleteBookingUser(@PathVariable Long id) throws ParseException {
        Optional<Booking> booking = bookingService.findById(id);
        booking.get().setCancelled(Boolean.TRUE);
        booking.get().getHome().setIsBooking(Boolean.FALSE);
        String checkin = booking.get().getCheckin();
        String checkout = booking.get().getCheckout();
        int totalDays = DateToMilisecond.totalDay(checkin,checkout);
        Result<String> result = new Result<>();
        result.setData("Khong the xoa 1 ngay");
        result.setSuccess("That bai");
        if (booking != null && totalDays!= 1) {
            if(totalDays == 0){
                return new ResponseEntity<>(new ResponseMessage("No Delete Before 1 Day"), HttpStatus.BAD_REQUEST);
            }
            bookingService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
