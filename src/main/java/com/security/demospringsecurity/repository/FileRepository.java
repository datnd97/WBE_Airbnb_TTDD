package com.security.demospringsecurity.repository;

import com.security.demospringsecurity.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileModel,Long> {
}
