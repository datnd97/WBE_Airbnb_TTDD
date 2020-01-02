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


   @ManyToMany(targetEntity = Home.class, fetch = FetchType.EAGER)
   @JoinTable(
           name = "project_booking",
           joinColumns = @JoinColumn(name = "booking_id"),
           inverseJoinColumns = @JoinColumn(name = "home_id")
   )
   private Set<Home> homeSet;

    public Booking() {
    }

    public Set<Home> getHomeSet() {
        return homeSet;
    }

    public void setHomeSet(Set<Home> homeSet) {
        this.homeSet = homeSet;
    }
}
