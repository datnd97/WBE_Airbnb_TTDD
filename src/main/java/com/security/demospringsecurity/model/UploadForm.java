package com.security.demospringsecurity.model;

import javax.persistence.ManyToOne;

public class UploadForm {
    private String name,address;
    private Integer bedroom, bathroom;
    private Double price;
    private TypeHome typeHome;
    private TypeRoom typeRoom;

    public UploadForm() {
    }

    public UploadForm(String name, String address, Integer bedroom, Integer bathroom, Double price, TypeHome typeHome, TypeRoom typeRoom) {
        this.name = name;
        this.address = address;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.price = price;
        this.typeHome = typeHome;
        this.typeRoom = typeRoom;
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

    public Integer getBedroom() {
        return bedroom;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public Integer getBathroom() {
        return bathroom;
    }

    public void setBathroom(Integer bathroom) {
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

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }
}
