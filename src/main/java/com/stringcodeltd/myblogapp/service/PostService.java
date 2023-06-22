package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDto);
    List<PostDTO> getAllPost();
}
