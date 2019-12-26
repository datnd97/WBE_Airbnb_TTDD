package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Status;

import java.util.Optional;

public interface StatusService {
    Iterable<Status> findAll();
    Optional<Status> findById(Long id);
    Status save(Status status);
    void delete(Long id);
}
