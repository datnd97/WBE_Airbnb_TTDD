package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Iterable<Image> findImagesByHomeId(Long id);
}
