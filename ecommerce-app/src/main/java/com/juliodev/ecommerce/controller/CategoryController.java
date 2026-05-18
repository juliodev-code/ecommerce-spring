package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.payload.CategoryDTO;
import com.juliodev.ecommerce.payload.CategoryResponse;
import com.juliodev.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<CategoryResponse> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO, categoryId), HttpStatus.OK);
    }


}
