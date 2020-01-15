package com.security.demospringsecurity.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file);
    Resource loadFile(String filename);
    void deleteAll();
    void init();
}
