package com.stringcodeltd.myblogapp.controller;

import com.stringcodeltd.myblogapp.dto.CommentDTO;
import com.stringcodeltd.myblogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{postid}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postid") long postId, @RequestBody CommentDTO commentDTO){
        CommentDTO commentnew = commentService.createComment(postId, commentDTO);
        return new ResponseEntity<>(commentnew, HttpStatus.CREATED);

    }
}
