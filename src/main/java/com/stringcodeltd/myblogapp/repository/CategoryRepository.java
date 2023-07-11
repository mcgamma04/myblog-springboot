package com.stringcodeltd.myblogapp.repository;

import com.stringcodeltd.myblogapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
// List<Category> findCategoryByName(long categoryId);
}
