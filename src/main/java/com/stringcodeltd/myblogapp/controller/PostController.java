package com.stringcodeltd.myblogapp.controller;

import com.stringcodeltd.myblogapp.dto.PostDTO;
import com.stringcodeltd.myblogapp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
     return  new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDTO> getAll(){
         return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long id){
        return ResponseEntity.ok( postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id")long id, @RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.updatePost(postDTO,id));
    }

}
