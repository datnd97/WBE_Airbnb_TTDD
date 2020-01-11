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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date da
    @Lob
    private Long children;

    @Lob
    private Long guest;

    @Lob
    private Boolean cancelled;

    @ManyToOne
    private Home home;

    @ManyToOne
    User user;

    public Booking(String dateCreate, String dateEnd, Long children, Long guest, Boolean cancelled, Home home, User user) {
        this.dateCreate = dateCreate;
        this.dateEnd = dateEnd;
        this.children = children;
        this.guest = guest;
        this.cancelled = cancelled;
        this.home = home;
        this.user = user;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }



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




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booking() {
    }


}
