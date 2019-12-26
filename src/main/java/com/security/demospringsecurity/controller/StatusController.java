package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.Status;
import com.security.demospringsecurity.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<?> listHome() {
        List<Status> homes = (List<Status>) statusService.findAll();
        if(homes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homes,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getHome(@PathVariable Long id) {
        Optional<Status> status = statusService.findById(id);
        if(status.isPresent()) {
            return new ResponseEntity<>(status,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createHome(@Valid @RequestBody Status status) {
        statusService.save(status);
        return new ResponseEntity<>(status,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHome(@PathVariable Long id,@RequestBody Status status) {
        Optional<Status> current = statusService.findById(id);
        if(!current.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        current.get().setName(status.getName());
        return new ResponseEntity<>(current.get(),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHome (@PathVariable Long id) {
        Optional<Status> status = statusService.findById(id);
        if(status.isPresent()) {
            statusService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
