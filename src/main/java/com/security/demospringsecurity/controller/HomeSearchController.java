package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.request.SearchHomeByNameForm;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.repository.HomeRepository;
import com.security.demospringsecurity.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/auth/homes")
public class HomeSearchController {
    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private HomeService homeService;
    @RequestMapping(value="/search")
    public ResponseEntity<?> method9(@RequestParam("min") String min, @RequestParam("max") String max ){
        double so1 = Double.parseDouble(min);
        double so2 = Double.parseDouble(max);
        List<Home> homes = homeRepository.findAllByPriceBetween(so1,so2);
        return new ResponseEntity<>(homes,HttpStatus.OK);
    };

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
