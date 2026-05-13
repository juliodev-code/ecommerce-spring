package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.model.Category;
import com.juliodev.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //@GetMapping("/api/public/categories")
    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    ResponseEntity<String>updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId){
        categoryService.updateCategory(category, categoryId);
        return new ResponseEntity<>("Category " + categoryId + " Updated", HttpStatus.OK);
    }


}
