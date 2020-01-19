package com.security.demospringsecurity.service.impl;

import com.security.demospringsecurity.model.Comment;
import com.security.demospringsecurity.repository.CommentRepository;
import com.security.demospringsecurity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICommentService implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findCommentByUserId(Long id) {
        return null;
    }
}
