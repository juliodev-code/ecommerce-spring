package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.model.Category;
import com.juliodev.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
            return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
        }
        catch(ResponseStatusException ex){
            return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
        }
    }

    @PutMapping("/{categoryId}")
    ResponseEntity<String>updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
        try{
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category " + categoryId + " Updated", HttpStatus.OK);
        }
        catch(ResponseStatusException ex){
            return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
        }
    }


}
