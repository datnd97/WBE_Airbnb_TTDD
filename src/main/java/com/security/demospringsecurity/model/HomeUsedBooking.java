package com.security.demospringsecurity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "home_used_booking")
public class HomeUsedBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Booking booking;

    @OneToOne
    private User user;

}
