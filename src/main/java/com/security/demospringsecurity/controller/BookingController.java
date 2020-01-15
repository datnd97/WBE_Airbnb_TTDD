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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/booking")
public class BookingController {

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
        System.out.println("Da vao day");
        List<Booking> bookings = (List<Booking>) bookingService.findAll();
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }
    // Fail:
    @RequestMapping(value = "find",method = RequestMethod.GET
            ,produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> findByDate(@RequestParam(value = "checkin") String checkin,
                                       @RequestParam(value = "checkout") String checkout) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(checkin);
        Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(checkout);
        return new ResponseEntity<>(bookingRepository.findAllByCheckinAfterAndAndCheckout(date1,date2),HttpStatus.OK);
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

    @GetMapping("/list-booking-user")
    public ResponseEntity<?> listBookingByUser() {
        Long userId = getCurrentUser().getId();
        User user = userService.findById(userId);
        List<Booking> bookings = bookingRepository.findAllByUser(user);
        ArrayList<Home> homes = new ArrayList<Home>();
        for(int i = 0 ; i < bookings.size(); i++) {
            homes.add(bookings.get(i).getHome());
        }
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }

}
