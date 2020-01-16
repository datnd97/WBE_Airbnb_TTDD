package com.security.demospringsecurity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.demospringsecurity.message.request.SearchHomeByNameForm;
import com.security.demospringsecurity.message.request.StatusForm;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.UploadForm;
import com.security.demospringsecurity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/homes")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @Autowired
    Environment env;

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
    public ResponseEntity<Home> createHome(@RequestParam("upload") String upload_form,
                                        @RequestParam("image") Optional<MultipartFile> image) throws IOException {
        UploadForm uploadForm = new ObjectMapper().readValue(upload_form, UploadForm.class);

        Home home = new Home();
        home.setName(uploadForm.getName());
        home.setDescription(uploadForm.getAddress());
        home.setBedroom(uploadForm.getBedroom());
        home.setBathroom(uploadForm.getBathroom());
        home.setPrice(uploadForm.getPrice());
        home.setTypeHome(uploadForm.getTypeHome());
        home.setTypeRoom(uploadForm.getTypeRoom());
        doUpload(home, image);
        homeService.save(home);
        return new ResponseEntity<>(home,HttpStatus.CREATED);
    }

    private void doUpload( Home home ,Optional<MultipartFile> fileImage) throws IOException{
        if (fileImage.isPresent()){
            String fileuploadFileImage = env.getProperty("uploadPath").toString();
            String fileNameImage = fileImage.get().getOriginalFilename();
            if (!fileNameImage.equals("")){
                FileCopyUtils.copy(fileImage.get().getBytes(), new File(fileuploadFileImage+ fileNameImage));
                home.setPicture(fileNameImage);
            }

        }
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
