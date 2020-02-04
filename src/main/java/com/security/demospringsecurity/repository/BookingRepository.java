package com.security.demospringsecurity.repository;


import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findBookingByUserId(Long id);
    List<Booking>  findAllByHome(List<Home> homes);
    List<Booking> findAllByUser(User user);
    Booking findByUserIdAndId(Long userId,Long bookingId);

}
