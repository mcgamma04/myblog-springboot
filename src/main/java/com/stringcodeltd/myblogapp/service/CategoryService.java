package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.CategoryDTO;
import com.stringcodeltd.myblogapp.dto.CommentDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(long categoryId);

    List<CategoryDTO> getCategory();

    void deleteCategory(long categoryId);
}
