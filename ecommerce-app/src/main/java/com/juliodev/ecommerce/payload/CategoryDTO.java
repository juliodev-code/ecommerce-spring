package com.juliodev.ecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @Schema(description = "Category Identifier")
    private Long categoryId;
    @Schema(description = "Category Name to insert")
    private String categoryName;
}
