package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.config.AppConstants;
import com.juliodev.ecommerce.payload.CategoryDTO;
import com.juliodev.ecommerce.payload.CategoryResponse;
import com.juliodev.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/categories")
@Tag(name="Category API", description = "Endpoints to manage Categories for classify products")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    @Operation(summary="List All Categories",
            description = "Endpoint to show all categories registered into Ecommerce application")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
            @RequestParam(name="pageSize", defaultValue = AppConstants.PAGE_SIZE, required=false) Integer pageSize,
            @RequestParam(name="sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required=false) String sortBy,
            @RequestParam(name="sortOrder", defaultValue = AppConstants.SORT_DIR, required=false) String sortOrder){
        return new ResponseEntity<>(categoryService.getAllCategories(pageNumber,pageSize, sortBy, sortOrder), HttpStatus.OK);
    }

    @PostMapping()
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Category was created successfully"),
            @ApiResponse(responseCode = "400",description = "Invalid Input", content = @Content),
            @ApiResponse(responseCode = "500",description = "Internal server error", content = @Content),
    })
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(
            @Parameter(description = "Category Id reference that you wish to delete")
            @PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO, categoryId), HttpStatus.OK);
    }


}
