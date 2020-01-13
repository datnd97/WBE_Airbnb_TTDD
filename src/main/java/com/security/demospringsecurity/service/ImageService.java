package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Image;

import java.util.Optional;

public interface ImageService {
    Optional<Image> findById(Long id);

    Iterable<Image> findAll();

    Image save(Image image);

    void delete(Long id);

//    Iterable<Image> findImagesByAlbumId(Long id);
}
