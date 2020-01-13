package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.response.ResponseMessage;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.ImageForm;
import com.security.demospringsecurity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/auth/upload")
public class UploadController {
    @Autowired
    private HomeService homeService;
    @Autowired
    Environment environment;
    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@ModelAttribute ImageForm imageForm, BindingResult result,@PathVariable Long id){
        if(result.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MultipartFile multipartFile = imageForm.getFile();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("uploadPath").toString();
        try {
            FileCopyUtils.copy(imageForm.getFile().getBytes(), new File(fileUpload + fileName));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        Optional<Home> home = homeService.findById(id);
        if(!home.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("home not found"),HttpStatus.NOT_FOUND);
        }
        home.get().setFileName(fileName);
        homeService.save(home.get());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
