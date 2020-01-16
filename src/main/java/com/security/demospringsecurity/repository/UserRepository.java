package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);


}
