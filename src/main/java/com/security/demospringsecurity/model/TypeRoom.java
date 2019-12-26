package com.security.demospringsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_room")
public class TypeRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String name;

//    @JsonIgnore
//    @OneToMany(targetEntity = Home.class,mappedBy = "type_room",cascade = CascadeType.ALL)
//    private List<Home> homes;

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

//    public List<Home> getHomes() {
//        return homes;
//    }
//
//    public void setHomes(List<Home> homes) {
//        this.homes = homes;
//    }

    public TypeRoom() {
    }

//    public TypeRoom(String name, List<Home> homes) {
//        this.name = name;
//        this.homes = homes;
//    }

    public TypeRoom(String name) {
        this.name = name;
    }
}
