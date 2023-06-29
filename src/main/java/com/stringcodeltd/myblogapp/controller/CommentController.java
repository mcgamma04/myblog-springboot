package com.stringcodeltd.myblogapp.controller;

import com.stringcodeltd.myblogapp.dto.CommentDTO;
import com.stringcodeltd.myblogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postid}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postid") long postId, @RequestBody CommentDTO commentDTO){
        CommentDTO comment_new = commentService.createComment(postId, commentDTO);
        return new ResponseEntity<>(comment_new, HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postid}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable(value = "postid") long postId){
        List<CommentDTO> commentByPostId = commentService.getCommentByPostId(postId);
        return new ResponseEntity<>(commentByPostId,HttpStatus.OK);

    }

    @GetMapping("/posts/{postid}/comments/{commentid}")
    public ResponseEntity<CommentDTO>  getCommentById(@PathVariable(value = "postid") long postid, @PathVariable(value = "commentid") long commentid){
     return new ResponseEntity<>(commentService.getCommentById(commentid,postid),HttpStatus.OK);
    }

    @PutMapping("/posts/{postid}/comments/{commentid}")

    public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postid")long postid,
                                                    @PathVariable(value = "commentid")long commentid,
                                                    @RequestBody CommentDTO commentDTO){
        CommentDTO updatedComment = commentService.updateComment(postid, commentid, commentDTO);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);

    }

    @DeleteMapping("/posts/{postid}/comments/{commentid}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postid") long postid, @PathVariable(value = "commentid") long commentid){
   commentService.deletCommentByPostId(postid,commentid);
   return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
    }
}
