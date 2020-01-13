package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
//    Iterable<Image> findImageByHomeId(Long id);
}
