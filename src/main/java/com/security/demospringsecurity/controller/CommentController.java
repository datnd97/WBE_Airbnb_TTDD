package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.model.Comment;
import com.security.demospringsecurity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<?> listCommnet() {
        List<Comment> comments = (List<Comment>) commentService.findAll();
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createCommnet(@RequestBody Comment comment) {
        commentService.save(comment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> comment1 = commentService.findById(id);
        if(!comment1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment1.get().setContent(comment.getContent());
        commentService.save(comment1.get());
        return new ResponseEntity<>(comment1.get(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            commentService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
