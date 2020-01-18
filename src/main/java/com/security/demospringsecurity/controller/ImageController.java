package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.Image;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.ImageService;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/host/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private HomeService homeService;
    @Autowired
    Environment env;

    @GetMapping
    public ResponseEntity<?> getListImage() {
        Iterable<Image> listImage = imageService.findAll();
        return new ResponseEntity<>(listImage, HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> createNewPicture(@RequestParam("name") MultipartFile image, @PathVariable Long id) throws IOException{
        Home home = homeService.findById(id);
        Image image1 = new Image();
        String fileUploadFileImage = env.getProperty("uploadPath").toString();
        String fileName = image.getOriginalFilename();
        if (!fileName.equals("")){
            FileCopyUtils.copy(image.getBytes(), new File(fileUploadFileImage + fileName));
            image1.setUrl("http://localhost:8080/file/");
            image1.setName(fileName);
        }
        image1.setHome(home);
        imageService.save(image1);
        return new ResponseEntity<>(image1,HttpStatus.OK);
    }

}


