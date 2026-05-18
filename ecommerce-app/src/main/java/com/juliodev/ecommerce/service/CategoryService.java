package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.model.Category;
import com.juliodev.ecommerce.payload.CategoryDTO;
import com.juliodev.ecommerce.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);

}
