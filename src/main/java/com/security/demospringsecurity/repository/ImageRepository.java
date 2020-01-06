package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ImageRepository extends JpaRepository<Image,Long> {
}
