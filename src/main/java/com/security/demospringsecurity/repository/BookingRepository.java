package com.security.demospringsecurity.repository;


import com.security.demospringsecurity.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

}
