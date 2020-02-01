package com.security.demospringsecurity.controller;


import com.security.demospringsecurity.message.request.UserForm;
import com.security.demospringsecurity.message.response.JwtResponse;
import com.security.demospringsecurity.model.*;
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
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        return ResponseEntity.ok(new AuthToken(userPrinciple.getId(),userPrinciple.getName(),token,roleName.getAuthority()));
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public User changepassword(@RequestBody UpdatePasswordDto user) {
        return userService.updatePassword(user);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserForm user) {
        User user1 = userService.findById(id);
//        if (user1 == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        user1.setName(user.getName());
        userService.save(user1);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

}