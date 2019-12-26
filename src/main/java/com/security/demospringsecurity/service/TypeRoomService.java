package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.TypeRoom;

import java.util.Optional;

public interface TypeRoomService {
    Iterable<TypeRoom> findAll();
    Optional<TypeRoom> findById(Long id);
    TypeRoom save(TypeRoom typeRoom);
    void delete(Long id);
}
