package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.TypeRoom;
import com.security.demospringsecurity.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/type-room")
public class TypeRoomController {
    @Autowired
    private TypeRoomService typeRoomService;

    @GetMapping
    public ResponseEntity<?> listType() {
        List<TypeRoom> type = (List<TypeRoom>) typeRoomService.findAll();
        if(type.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(type, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getType(@PathVariable Long id) {
        Optional<TypeRoom> home = typeRoomService.findById(id);
        if(home.isPresent()) {
            return new ResponseEntity<>(home,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createType(@Valid @RequestBody TypeRoom type) {
        typeRoomService.save(type);
        return new ResponseEntity<>(type,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateType(@PathVariable Long id,@RequestBody TypeRoom type) {
        Optional<TypeRoom> currentType = typeRoomService.findById(id);
        if(!currentType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentType.get().setName(type.getName());
        return new ResponseEntity<>(currentType.get(),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteType(@PathVariable Long id) {
        Optional<TypeRoom> type = typeRoomService.findById(id);
        if(type.isPresent()) {
            typeRoomService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
