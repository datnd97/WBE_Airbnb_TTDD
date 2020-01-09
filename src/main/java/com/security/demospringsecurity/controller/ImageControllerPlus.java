package com.security.demospringsecurity.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.Image;
import com.security.demospringsecurity.model.View;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.ImageService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth/images")
public class ImageControllerPlus {
    @Autowired
    private ImageService imageService;
    @Autowired
    private HomeService homeService;
    @PostMapping("/upload")
    public String createFileUpload(@RequestParam("file") MultipartFile file,@PathVariable Long id){
        Optional<Home> home = homeService.findById(id);
        try {
            Image filemode = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            filemode.setHome(home.get());
            imageService.save(filemode);
            return ("File uploaded successfully! -> filename = " + file.getOriginalFilename());
        } catch (  Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<Image> fileOptional = imageService.findById(id);

        if(fileOptional.isPresent()) {
            Image file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getPic());
        }

        return ResponseEntity.status(404).body(null);
    }
    @JsonView(View.FileInfo.class)
    @GetMapping
    public Iterable<Image> getListFiles() {
        return imageService.findAll();
    }
}
