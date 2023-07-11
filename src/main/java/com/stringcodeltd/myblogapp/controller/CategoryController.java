package com.stringcodeltd.myblogapp.controller;

import com.stringcodeltd.myblogapp.dto.CategoryDTO;
import com.stringcodeltd.myblogapp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO comment = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO>getCategoryById(@PathVariable(value = "id")long categoryid){
        CategoryDTO categoryById = categoryService.getCategoryById(categoryid);
        return new ResponseEntity<>(categoryById,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategory(){
        List<CategoryDTO> category = categoryService.getCategory();
        return new ResponseEntity<>(category,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecategory(@PathVariable(value = "id") long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully",HttpStatus.OK);
    }
}
