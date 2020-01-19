package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.Image;
import com.security.demospringsecurity.repository.ImageRepository;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/host")

public class HostController {

    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Autowired
    private ImageService imageService;

    @Autowired
    Environment env;

    @Autowired
    private HomeService homeService;

    @PostMapping("/create-home")
    public ResponseEntity<?> createHome(@Valid @RequestBody Home home) {

        homeService.save(home);
        return new ResponseEntity<>(home, HttpStatus.CREATED);
    }
    @GetMapping("/{id}/list-home-by-host")
    public ResponseEntity<?> listHomeByHost( @PathVariable Long id) {
        List<Home> homes = (List<Home>) homeService.findAllByUserId(id);
        if (homes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateHome(@PathVariable Long id, @RequestBody Home home) {
        Home currentHome = homeService.findById(id);
        if (currentHome == null) {
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
        return new ResponseEntity<>(currentHome, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHome(@PathVariable Long id) {
        Home home = homeService.findById(id);
        if (home != null) {
            homeService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/{id}/create-image")
    public ResponseEntity<?> createNewPicture(@RequestParam("name") MultipartFile image, @PathVariable Long id) throws IOException {
        Home home = homeService.findById(id);
        Image image1 = new Image();
        String fileUploadFileImage = env.getProperty("uploadPath").toString();
        String fileName = image.getOriginalFilename();
        if (!fileName.equals("")) {
            FileCopyUtils.copy(image.getBytes(), new File(fileUploadFileImage + fileName));
            image1.setUrl("http://localhost:8080/file/");
            image1.setName(fileName);
        }
        image1.setHome(home);
        imageService.save(image1);
        return new ResponseEntity<>(image1, HttpStatus.OK);
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getListImage(@PathVariable Long id) {
        Iterable<Image> listImage = imageService.findByIdHome(id);
        return new ResponseEntity<>(listImage, HttpStatus.OK);
    }

}
