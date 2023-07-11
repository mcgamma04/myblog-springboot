package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.CommentDTO;

public interface CategoryService {
    CommentDTO createComment(CommentDTO commentDTO);
}
