package com.stringcodeltd.myblogapp.controller;

import com.stringcodeltd.myblogapp.dto.PostDTO;
import com.stringcodeltd.myblogapp.dto.PostResponse;
import com.stringcodeltd.myblogapp.service.PostService;
import com.stringcodeltd.myblogapp.util.AppConstant;
import jakarta.validation.Valid;
import lombok.Data;
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
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
     return  new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NO, required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir",defaultValue = AppConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
         return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long id){
        return ResponseEntity.ok( postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id")long id, @Valid @RequestBody PostDTO postDTO){
        PostDTO postFeedBack = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postFeedBack,HttpStatus.OK);
    }
@DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
    postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully",HttpStatus.OK);
}



}
