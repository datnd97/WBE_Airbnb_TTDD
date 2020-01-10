package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.request.SearchHomeByNameForm;
import com.security.demospringsecurity.message.request.StatusForm;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/homes")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping
    public ResponseEntity<?> listHome() {
        List<Home> homes = (List<Home>) homeService.findAll();
        if(homes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homes,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHome(@PathVariable Long id) {
        Home home = homeService.findById(id);
        if(home != null) {
            return new ResponseEntity<>(home,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> createHome(@Valid @RequestBody Home home) {
        
        homeService.save(home);
        return new ResponseEntity<>(home,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHome(@PathVariable Long id,@RequestBody Home home) {
        Home currentHome = homeService.findById(id);
        if(currentHome == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentHome.setName(home.getName());
        currentHome.setAddress(home.getAddress());
        currentHome.setBathroom(home.getBathroom());
        currentHome.setBedroom(home.getBedroom());
        currentHome.setDescription(home.getDescription());
        currentHome.setPrice(home.getPrice());
        currentHome.setTypeHome(home.getTypeHome());
        currentHome.setTypeRoom(home.getTypeRoom());
        currentHome.setStatus(home.getStatus());
        homeService.save(currentHome);
        return new ResponseEntity<>(currentHome,HttpStatus.CREATED);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,@RequestBody StatusForm status) {
        Home current = homeService.findById(id);
        if(current ==null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        current.setStatus(status.getStatus());
        return new ResponseEntity<>(current,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHome(@PathVariable Long id) {
        Home home = homeService.findById(id);
        if(home!= null) {
            homeService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/search-by-name")
    public ResponseEntity<?> searchHomeName(@RequestBody SearchHomeByNameForm form) {
        if(form.getName().equals("") || form.getName() == null) {
            List<Home> homes = (List<Home>) homeService.findAll();
            if(homes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(homes, HttpStatus.OK);
            }
        }
        else {
            List<Home> homes = (List<Home>) homeService.findAllByName(form.getName());
            if(homes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(homes,HttpStatus.OK);
            }
        }

    }



}
