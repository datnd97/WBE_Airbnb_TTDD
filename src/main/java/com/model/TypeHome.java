package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_home")
@Data
public class TypeHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "typeHome",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Home> homes;


}
