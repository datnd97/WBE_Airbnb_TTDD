package com.security.demospringsecurity.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.security.demospringsecurity.model.FileModel;
import com.security.demospringsecurity.model.View;
import com.security.demospringsecurity.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/image")
public class UploadFileController {
    @Autowired
    FileRepository fileRepository;
    /*
     * MultipartFile Upload
     */
    @PostMapping("/upload")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file) {
        try {
            FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileRepository.save(filemode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (  Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }
    /*
     * List All Files
     */
    @JsonView(View.FileInfo.class)
    @GetMapping("/all")
    public List<FileModel> getListFiles() {
        return fileRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileModel> fileOptional = fileRepository.findById(id);

        if(fileOptional.isPresent()) {
            FileModel file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getPic());
        }

        return ResponseEntity.status(404).body(null);
    }
}
