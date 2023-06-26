package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(long postId, CommentDTO commentDTO);



}
