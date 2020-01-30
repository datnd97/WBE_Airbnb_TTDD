package com.security.demospringsecurity.service;

import com.security.demospringsecurity.model.Comment;
import com.security.demospringsecurity.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Iterable<Comment> findAll();
    Optional<Comment> findById(Long id);
    Comment save(Comment Comment);
    void delete(Long id);
    List<Comment> findCommentByUserId(Long id);
    List<Comment> findAllByHomeId(Long id);

}
