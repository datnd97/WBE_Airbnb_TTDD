package com.security.demospringsecurity.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonView(View.FileInfo.class)
    private Long id;

    @Column(name = "name")
    @JsonView(View.FileInfo.class)
    private String name;

    @Column(name = "mimetype")
    private String mimetype;

    @Lob
    @Column(name="pic")
    private byte[] pic;

    @ManyToOne
    private Home home;

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Image(String name, String mimetype, byte[] pic, Home home) {
        this.name = name;
        this.mimetype = mimetype;
        this.pic = pic;
        this.home = home;
    }

    public Image() {
    }

    public Image(String name, String mimetype, byte[] pic) {
        this.name = name;
        this.mimetype = mimetype;
        this.pic = pic;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMimetype(){
        return this.mimetype;
    }

    public void setMimetype(String mimetype){
        this.mimetype = mimetype;
    }

    public byte[] getPic(){
        return this.pic;
    }

    public void setPic(byte[] pic){
        this.pic = pic;
    }
}
