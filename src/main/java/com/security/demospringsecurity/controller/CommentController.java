package com.security.demospringsecurity.controller;

import com.security.demospringsecurity.message.response.ResponseMessage;
import com.security.demospringsecurity.model.Comment;
import com.security.demospringsecurity.model.Home;
import com.security.demospringsecurity.model.TypeHome;
import com.security.demospringsecurity.model.TypeRoom;
import com.security.demospringsecurity.security.service.UserPrinciple;
import com.security.demospringsecurity.service.CommentService;
import com.security.demospringsecurity.service.HomeService;
import com.security.demospringsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private HomeService homeService;
    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
    @GetMapping("/{homeId}")
    public ResponseEntity<?> listCommnetByHomeId(@PathVariable Long homeId) {
        List<Comment> comments = commentService.findAllByHomeId(homeId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping("/{homeId}")
    public ResponseEntity<ResponseMessage> createCommnet(@RequestBody Comment comment, @PathVariable Long homeId) {
        if(comment.getContent() == null || comment.getContent() == ""){
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(false, "You have not comment this home!", null), HttpStatus.NO_CONTENT);
        }
        comment.setUser(userService.findById(getCurrentUser().getId()));
        Home home = homeService.findById(homeId);
        comment.setHome(home);
        commentService.save(comment);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Comment Successful", comment),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            commentService.delete(id);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editComment(@PathVariable Long id,@RequestBody Comment comment) {
        Optional<Comment> commentCurrent = commentService.findById(id);
        if(commentCurrent.isPresent()) {
            commentCurrent.get().setContent(comment.getContent());
            commentService.save(commentCurrent.get());
            return new ResponseEntity<>(commentCurrent.get(),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
