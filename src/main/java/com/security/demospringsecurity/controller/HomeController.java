package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.request.SearchHomeByNameForm;
import com.security.demospringsecurity.message.request.StatusForm;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.Image;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.repository.ImageRepository;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.ImageService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<?> listHome() {
        List<Home> homes = (List<Home>) homeService.findAll();
        if (homes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHome(@PathVariable Long id) {
        Home home = homeService.findById(id);
        if (home != null) {
            return new ResponseEntity<>(home, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/images")
//    public ResponseEntity<?> getListImage() {
//        Iterable<Image> listImage = imageService.findAll();
//        return new ResponseEntity<>(listImage, HttpStatus.OK);
//    }

//    @GetMapping("/cars")
//    public ResponseEntity<List<Home>> searchForCars(@SearchSpec Specification<Home> specs) {
//        return new ResponseEntity<>(homeRepository.findAll(Specification.where(specs)), HttpStatus.OK);
//    }

}
