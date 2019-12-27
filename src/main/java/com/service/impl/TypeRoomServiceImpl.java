package com.service.impl;

import com.model.TypeRoom;
import com.repository.TypeRoomRepository;
import com.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TypeRoomServiceImpl implements TypeRoomService {
    @Autowired
    TypeRoomRepository typeRoomRepository;
    @Override
    public Iterable<TypeRoom> findAll() {
        return typeRoomRepository.findAll();
    }

    @Override
    public Optional<TypeRoom> findById(Long id) {
        return typeRoomRepository.findById(id);
    }

    @Override
    public TypeRoom save(TypeRoom typeRoom) {
        return typeRoomRepository.save(typeRoom);
    }

    @Override
    public void delete(Long id) {
        typeRoomRepository.deleteById(id);
    }
}
