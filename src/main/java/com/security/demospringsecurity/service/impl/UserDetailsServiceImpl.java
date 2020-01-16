package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.login.*;
import com.security.demospringsecurity.repository.RolesRepository;
import com.security.demospringsecurity.repository.UserRepository;
import com.security.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserRepository userDao;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public User save(UserDto user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser = userDao.save(newUser);
        return newUser;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }
    
    @Override
    public User updatePassword(UpdatePasswordDto user) {
        User currentUser = userDao.findByUsername(user.getUsername());
        if (currentUser != null) {
            String username = currentUser.getUsername();
            String password = currentUser.getPassword();
            if (username.equals(user.getUsername()) && bcryptEncoder.matches(user.getCurrentPassword(), password)) {
                if (user.getNewPassword().equals(user.getConfirmNewPassword())) {
                    User updatePassUser = userDao.findById(currentUser.getId()).get();
                    updatePassUser.setPassword(bcryptEncoder.encode(user.getNewPassword()));
                    userDao.save(updatePassUser);
                    return updatePassUser;
                }
            }
        }
        return null;
    }
}
