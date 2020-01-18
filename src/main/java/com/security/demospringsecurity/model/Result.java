package com.security.demospringsecurity.model;

import lombok.Data;

@Data
public class Result<E> {
    private String success;
    private E data;


}
