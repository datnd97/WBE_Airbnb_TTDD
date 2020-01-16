package com.security.demospringsecurity.repository;


import com.security.demospringsecurity.model.Booking;
import com.security.demospringsecurity.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    ArrayList<Booking> findAllByUser(User user);
    Booking findByUserAndId(User user,Long id);
}
