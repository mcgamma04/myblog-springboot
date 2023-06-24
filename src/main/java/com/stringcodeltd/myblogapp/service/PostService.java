package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDto);
    List<PostDTO> getAllPost();
    PostDTO getPostById(long id);
    PostDTO updatePost(PostDTO postDTO, long id);
    void deletePost(long id);
}
