package com.security.demospringsecurity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.apache.catalina.Store;
import org.jboss.logging.Field;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "home")
@Data
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String name;

    @Lob
    private String address;
    @Lob
    private Integer bedroom;
    @Lob
    private Integer bathroom;
    @Lob
    private Double price;
    @ManyToOne
    private TypeHome typeHome;
    @ManyToOne
    private TypeRoom typeRoom;

    @JsonIgnore
    @OneToMany(targetEntity = Booking.class)
    private List<Booking> booking;

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(targetEntity = Image.class,mappedBy = "home",cascade = CascadeType.ALL)
    private List<Image> images;

//    @JsonIgnore
    private Boolean isBooking;


    @Lob
    private String description;

    private Boolean status;



}
