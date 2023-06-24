package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.PostDTO;
import com.stringcodeltd.myblogapp.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDto);
    PostResponse getAllPost(int pageNo, int PageSize);
    PostDTO getPostById(long id);
    PostDTO updatePost(PostDTO postDTO, long id);
    void deletePost(long id);
}
