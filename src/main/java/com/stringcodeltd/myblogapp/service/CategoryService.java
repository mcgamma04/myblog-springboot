package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.CategoryDTO;
import com.stringcodeltd.myblogapp.dto.CommentDTO;

public interface CategoryService {
    CategoryDTO createComment(CategoryDTO categoryDTO);
}
