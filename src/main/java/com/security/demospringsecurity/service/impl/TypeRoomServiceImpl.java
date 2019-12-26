package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.TypeRoom;
import com.security.demospringsecurity.repository.TypeRoomRepository;
import com.security.demospringsecurity.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeRoomServiceImpl  implements TypeRoomService {
    @Autowired
    private TypeRoomRepository typeRoomRepository;
    @Override
    public Iterable<TypeRoom> findAll() {
        return typeRoomRepository.findAll();
    }

    @Override
    public Optional<TypeRoom> findById(Long id) {
        return typeRoomRepository.findById(id);
    }

    @Override
    public TypeRoom save(TypeRoom typeHome) {
        return typeRoomRepository.save(typeHome);
    }

    @Override
    public void delete(Long id) {
        typeRoomRepository.deleteById(id);
    }
}
