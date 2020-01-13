package com.security.demospringsecurity.model;


import javax.persistence.*;

@Entity
@Table(name = "image")
public
class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    @ManyToOne
    @JoinColumn(name = "home_id")
    private Home home;

    public Image() {
    }
    public Image(String path, Home home){
        this.path = path;
        this.home = home;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}