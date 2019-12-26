package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.repository.TypeHomeRepository;
import com.security.demospringsecurity.service.TypeHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeHomeServiceImpl implements TypeHomeService {
    @Autowired
    private TypeHomeRepository typeHomeRepository;
    @Override
    public Iterable<TypeHome> findAll() {
        return typeHomeRepository.findAll();
    }

    @Override
    public Optional<TypeHome> findById(Long id) {
        return typeHomeRepository.findById(id);
    }

    @Override
    public TypeHome save(TypeHome typeHome) {
        return typeHomeRepository.save(typeHome);
    }

    @Override
    public void delete(Long id) {
        typeHomeRepository.deleteById(id);
    }
}
