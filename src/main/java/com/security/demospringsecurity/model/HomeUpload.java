package com.security.demospringsecurity.model;

import org.springframework.web.multipart.MultipartFile;

public class HomeUpload {
    private Home home;
    private MultipartFile[] files;

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
