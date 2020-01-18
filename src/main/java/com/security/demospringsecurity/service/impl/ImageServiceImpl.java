package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.Image;
import com.security.demospringsecurity.repository.ImageRepository;
import com.security.demospringsecurity.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository repository;

    @Override
    public Optional<Image> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Image> findAll() {
        return repository.findAll();
    }

    @Override
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Image> findByIdHome(Long id) {
        return repository.findImagesByHomeId(id);
    }
}
