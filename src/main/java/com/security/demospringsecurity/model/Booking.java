package com.security.demospringsecurity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static com.security.demospringsecurity.util.DateParser.dateParser;
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date begin_date;
    private Date end_date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBegin_date() {
        return dateParser(begin_date,"yyyy-MM-dd");
    }
    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return dateParser(end_date,"yyyy-MM-dd");
    }
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    public Booking(Date begin_date, Date end_date) {
        this.begin_date = begin_date;
        this.end_date = end_date;

    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "booking_hotel",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "home_id")
    )
    private Set<Home> homes = new HashSet<>();

    public Set<Home> getHomes() {
        return homes;
    }

    public void setHomes(Set<Home> homes) {
        this.homes = homes;
    }

    public Booking() {
    }
}
