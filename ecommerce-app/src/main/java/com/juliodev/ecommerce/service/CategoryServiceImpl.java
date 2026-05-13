package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.exceptions.APIException;
import com.juliodev.ecommerce.exceptions.ResourceNotFoundException;
import com.juliodev.ecommerce.model.Category;
import com.juliodev.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {

        if(categoryRepository.count() == 0){
            throw new APIException("There are not categories to shown");
        }
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(savedCategory != null) throw new APIException(String.format("Category with the name %s already exists", category.getCategoryName()));
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isEmpty()){
            throw new ResourceNotFoundException("Category", "CategoryId", categoryId);
        }
        categoryRepository.delete(optionalCategory.get());
        return "Category deleted successfully";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(existingCategory);
        }
        else throw new ResourceNotFoundException("Category", "CategoryId", categoryId);
    }
}
