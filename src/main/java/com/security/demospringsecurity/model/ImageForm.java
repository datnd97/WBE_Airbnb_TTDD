package com.security.demospringsecurity.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {
    private MultipartFile file;
    public ImageForm(){

    }
    public ImageForm(MultipartFile file){
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}