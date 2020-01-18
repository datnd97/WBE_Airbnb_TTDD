package com.security.demospringsecurity.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;


@Entity
@Table(name = "image")
@Data
public class Image {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name;
   private String url;
   @ManyToOne
   private Home home;
}
