package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.TypeHome;

import java.util.Optional;

public interface TypeHomeService {
    Iterable<TypeHome> findAll();
    Optional<TypeHome> findById(Long id);
    TypeHome save(TypeHome typeHome);
    void delete(Long id);
}
