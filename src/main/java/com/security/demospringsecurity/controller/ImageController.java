package com.security.demospringsecurity.controller;

import com.fasterxml.jackson.annotation.JsonView;

import com.security.demospringsecurity.model.Image;
import com.security.demospringsecurity.model.View;

import com.security.demospringsecurity.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth/image")
public class ImageController {
    @Autowired
    ImageRepository fileRepository;
    /*
     * MultipartFile Upload
     */
    @PostMapping("/upload")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file) {
        try {
            // save file to PostgreSQL
            Image filemode = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileRepository.save(filemode);
            return ("File uploaded successfully! -> filename = " + file.getOriginalFilename());
        } catch (  Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }

    }

//    public String saveImage(MultipartFile file) {
//        try {
//            // save file to PostgreSQL
//            FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
//            fileRepository.save(filemode);
//            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
//        } catch (  Exception e) {
//            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
//        }
//    }
    /*
     * List All Files
     */
    @JsonView(View.FileInfo.class)
    @GetMapping("/all")
    public List<Image> getListFiles() {
        return fileRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<Image> fileOptional = fileRepository.findById(id);

        if(fileOptional.isPresent()) {
            Image file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getPic());
        }

        return ResponseEntity.status(404).body(null);
    }
}
