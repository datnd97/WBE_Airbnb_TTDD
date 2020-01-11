package com.security.demospringsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "typeHome")
public class TypeHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String name;

    @JsonIgnore
    @OneToMany(targetEntity = Home.class,mappedBy = "typeHome",cascade = CascadeType.ALL)
    private List<Home> homes;



}
