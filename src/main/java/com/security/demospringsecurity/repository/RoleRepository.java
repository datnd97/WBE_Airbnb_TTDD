package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Role;
import com.security.demospringsecurity.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleName roleName);
}
