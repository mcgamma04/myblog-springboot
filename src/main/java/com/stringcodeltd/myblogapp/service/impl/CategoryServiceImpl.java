package com.stringcodeltd.myblogapp.service.impl;

import com.stringcodeltd.myblogapp.dto.CategoryDTO;
import com.stringcodeltd.myblogapp.dto.CommentDTO;
import com.stringcodeltd.myblogapp.exception.ResourceNotFoundException;
import com.stringcodeltd.myblogapp.model.Category;
import com.stringcodeltd.myblogapp.model.Comment;
import com.stringcodeltd.myblogapp.repository.CategoryRepository;
import com.stringcodeltd.myblogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
     //find if the category exists
        Category category = mapper.map(categoryDTO, Category.class);
        Category categorResponse = categoryRepository.save(category);

        return mapper.map(categorResponse,CategoryDTO.class);
    }

    @Override
    public CategoryDTO getCategoryById(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        return mapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> mapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
    }
//    //convert entuty to DTO
//    private CategoryDTO mapToDTO(Category category){
//        return mapper.map(category,CategoryDTO.class);
//    }
//    //CONVERT DTO TO ENTITY
//  private Category mapToEntity(CategoryDTO categoryDTO){
//       return mapper.map(categoryDTO, Category.class);
//  }
}
