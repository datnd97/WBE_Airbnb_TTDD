package com.security.demospringsecurity.config;

import com.security.demospringsecurity.message.request.StatusForm;
import com.security.demospringsecurity.model.Home;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class FileEmty {
                                    // Upload file:

//    @PostMapping("/upload-images/{id}")
//    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("name") MultipartFile[] files,@PathVariable Long id) throws IOException {
//        Home home = homeService.findById(id);
//        List<Image> imageList = (List<Image>) new Image();
//        for(MultipartFile file: files) {
//            Image image = doUpload(file);
//            imageList.add(image);
//        }
//        home.setImages(imageList);
//        return new ResponseEntity<>(home.getImages(),HttpStatus.OK);
//    }
//    private Image doUpload(MultipartFile image) throws IOException {
//        Image image1 = new Image();
//        String fileName = image.getOriginalFilename();
//        String fileUploadFileImage = env.getProperty("uploadPath").toString();
//        FileCopyUtils.copy(image.getBytes(), new File(fileUploadFileImage + fileName));
//        image1.setName(fileName);
//        image1.setUrl("http://localhost:8080/file/");
//        return image1;
//    }

                            /*  File Status:*/
//@PutMapping("/{id}/status")
//public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody StatusForm status) {
//    Home current = homeService.findById(id);
//    if(current ==null ) {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    current.setStatus(status.getStatus());
//    return new ResponseEntity<>(current,HttpStatus.CREATED);
//}
}
