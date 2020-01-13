package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.request.FileForm;
import com.security.demospringsecurity.message.response.ResponseMessage;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UploadFileController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/home-file/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadHomeFile(@ModelAttribute FileForm fileForm ,  BindingResult result,@PathVariable Long id)throws IOException {
        try {
            if(result.hasErrors()) {
                return new ResponseEntity<>(new ResponseMessage("Upload file Fail"),HttpStatus.BAD_REQUEST);
            }
            MultipartFile multipartFile = fileForm.getFile();
            Home home = homeService.findById(id);
            if(home == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if(multipartFile != null) {
                String urlFile = multipartFile.getName();
                home.setUrlFile(urlFile);
            }
            homeService.save(home);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e ,  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
