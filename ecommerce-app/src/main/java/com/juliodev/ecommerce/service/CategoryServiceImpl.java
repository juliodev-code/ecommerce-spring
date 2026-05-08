package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final List<Category> categories = new ArrayList<>();
    private Long lastIdTaken = 0L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(++lastIdTaken);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId){
        Optional<Category> category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if(category.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
        }
        categories.remove(category.get());
        return "Category deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
    }
}
