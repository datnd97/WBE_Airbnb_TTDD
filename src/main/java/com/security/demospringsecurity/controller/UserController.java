package com.security.demospringsecurity.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.demospringsecurity.message.request.LoginForm;
import com.security.demospringsecurity.message.request.UserForm;
import com.security.demospringsecurity.message.response.JwtResponse;
import com.security.demospringsecurity.message.response.ResponseMessage;
import com.security.demospringsecurity.model.*;
import com.security.demospringsecurity.repository.UserRepository;
import com.security.demospringsecurity.security.jwt.JwtProvider;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private  UserRepository userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private JwtProvider jwtTokenUtil;
    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> listUser() {
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
//    @PreAuthorize("hasRole('USER')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id) {
        return userService.findById(id);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRoles() {
        return userService.getRoles();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginUser loginUser) throws AuthenticationException {
        System.out.print("Login Controller Start");

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateJwtToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        GrantedAuthority roleName = null;
        if (authentication.getAuthorities().size() > 0) {
            roleName = authentication.getAuthorities().iterator().next();
        }
        return ResponseEntity.ok(new AuthToken(userPrinciple.getId(),userPrinciple.getName(),token,roleName.getAuthority(),userPrinciple.getUsername()));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePasswordRequestParam(@RequestBody UpdatePasswordDto user) throws IOException {
        User currentUser = userDao.findByUsername(user.getUsername());
        if (currentUser != null) {
        String username = currentUser.getUsername();
        String password = currentUser.getPassword();
        if (username.equals(user.getUsername()) && bcryptEncoder.matches(user.getCurrentPassword(), password)) {
            if (user.getNewPassword().equals(user.getConfirmNewPassword())) {
                User updatePassUser = userDao.findById(currentUser.getId()).get();
                updatePassUser.setPassword(bcryptEncoder.encode(user.getNewPassword()));
                userDao.save(updatePassUser);
                return new ResponseEntity<>(new ResponseMessage("Password changed succces ;)"), HttpStatus.ACCEPTED);
            }
        }
    }
        return new ResponseEntity<>(new ResponseMessage("Wrong current password :("), HttpStatus.NOT_ACCEPTABLE);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserForm user) {
        User user1 = userService.findById(id);
        if (user1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            user1.setName(user.getName());
            userService.save(user1);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }
        catch (Exception e ) {
            throw new RuntimeException("Fail!");
        }
    }

}