package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Role;
import com.security.demospringsecurity.model.UpdatePasswordDto;
import com.security.demospringsecurity.model.User;
import com.security.demospringsecurity.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    User save(User user);
    List<User> findAll();

    void delete(long id);

    User findOne(String username);

    User findById(Long id);

    List<Role> getRoles();

    User updatePassword(UpdatePasswordDto user);
}
