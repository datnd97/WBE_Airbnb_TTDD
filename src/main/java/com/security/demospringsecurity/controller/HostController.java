package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.Image;
import com.security.demospringsecurity.repository.ImageRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/host")
public class HostController {

    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @Autowired
    private ImageService service;



    @Autowired
    private HomeService homeService;
    @GetMapping("/{id}/list-image-by-home")
    public ResponseEntity<?> listImageByHome(@PathVariable Long id) {
        List<Image> images = (List<Image>) service.findByIdHome(id);
        if (images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(images, HttpStatus.OK);
        }
    }

    @PostMapping("/create-home")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHome(@PathVariable Long id) {
        Home home = homeService.findById(id);
        if(home!= null) {
            homeService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
