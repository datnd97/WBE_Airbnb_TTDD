package com.security.demospringsecurity.service;


import com.security.demospringsecurity.model.Image;

import java.util.Optional;

public interface ImageService {
    Iterable<Image> findAll();
    Optional<Image> findById(Long id);
    void save(Image image);
    void remove(Long id);
    Iterable<Image> findAllByHome_HomeId(Long homeId);
}
