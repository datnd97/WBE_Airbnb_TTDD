package com.security.demospringsecurity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date checkin;
    private Date checkout;
    @Lob
    private Long children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    private Long guest;

    @Lob
    private Boolean cancelled;

    @ManyToOne
    private Home home;
    @ManyToOne
    private User user;

    private Date timeNow;

    public String  getTimeNow() {
        return dateParser(timeNow,"yyyy/MM/dd HH:mm:ss");
    }

    public void setTimeNow(Date timeNow) {
        this.timeNow = timeNow;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public String getCheckin() {
        return dateParser(checkin,"yyyy/MM/dd");
    }

    public String getCheckout() {
        return dateParser(checkout,"yyyy/MM/dd");
    };

    public Long getChildren() {
        return children;
    }

    public void setChildren(Long children) {
        this.children = children;
    }

    public Long getGuest() {
        return guest;
    }

    public void setGuest(Long guest) {
        this.guest = guest;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
