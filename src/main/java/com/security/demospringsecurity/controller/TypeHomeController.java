package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.repository.TypeHomeRepository;
import com.security.demospringsecurity.service.TypeHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/type-home")
public class TypeHomeController {
    @Autowired
    private TypeHomeService typeHomeService;

    @GetMapping
    public ResponseEntity<?> listType() {
        List<TypeHome> type = (List<TypeHome>) typeHomeService.findAll();
        if(type.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(type,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getType(@PathVariable Long id) {
        Optional<TypeHome> home = typeHomeService.findById(id);
        if(home.isPresent()) {
            return new ResponseEntity<>(home,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createType(@Valid @RequestBody TypeHome type) {
        typeHomeService.save(type);
        return new ResponseEntity<>(type,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateType(@PathVariable Long id,@RequestBody TypeHome type) {
        Optional<TypeHome> currentType = typeHomeService.findById(id);
        if(!currentType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentType.get().setName(type.getName());
        typeHomeService.save(currentType.get());
        return new ResponseEntity<>(currentType.get(),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteType(@PathVariable Long id) {
        Optional<TypeHome> type = typeHomeService.findById(id);
        if(type.isPresent()) {
            typeHomeService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
