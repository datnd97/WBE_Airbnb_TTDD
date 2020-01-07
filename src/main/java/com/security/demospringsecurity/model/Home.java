package com.security.demospringsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.catalina.Store;
import org.jboss.logging.Field;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "home")

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




    @Lob
    private String description;


    @Lob
    private Boolean status;



    @JsonIgnore
    @OneToMany(targetEntity = Image.class,cascade = CascadeType.ALL)
    private List<Image> images;

    public Home(String name, String address, Integer bedroom, Integer bathroom, Double price, TypeHome typeHome, TypeRoom typeRoom, String description, Boolean status, List<Image> images, Set<Booking> bookings) {
        this.name = name;
        this.address = address;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.price = price;
        this.typeHome = typeHome;
        this.typeRoom = typeRoom;
        this.description = description;
        this.status = status;
        this.images = images;
        this.bookings = bookings;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "homeSet",fetch = FetchType.EAGER)
    private Set<Booking> bookings;

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Home(String name, String address, Integer bedroom, Integer bathroom, Double price, TypeHome typeHome, TypeRoom typeRoom, String description, Boolean status) {
        this.name = name;
        this.address = address;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.price = price;
        this.typeHome = typeHome;
        this.typeRoom = typeRoom;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBedroom() {
        return bedroom;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TypeHome getTypeHome() {
        return typeHome;
    }

    public void setTypeHome(TypeHome typeHome) {
        this.typeHome = typeHome;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public void setBathroom(Integer bathroom) {
        this.bathroom = bathroom;
    }

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }



    public Home() {
    }
}
