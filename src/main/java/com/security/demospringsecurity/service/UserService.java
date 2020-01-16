package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.login.Role;
import com.security.demospringsecurity.model.login.UpdatePasswordDto;
import com.security.demospringsecurity.model.login.User;
import com.security.demospringsecurity.model.login.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);

    List<User> findAll();

    void delete(long id);

    User findOne(String username);

    User findById(Long id);

    User updatePassword(UpdatePasswordDto user);
}
