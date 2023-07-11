package com.stringcodeltd.myblogapp.repository;

import com.stringcodeltd.myblogapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
