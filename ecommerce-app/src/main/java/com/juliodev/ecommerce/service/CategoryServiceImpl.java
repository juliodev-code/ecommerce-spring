package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.exceptions.APIException;
import com.juliodev.ecommerce.exceptions.ResourceNotFoundException;
import com.juliodev.ecommerce.model.Category;
import com.juliodev.ecommerce.payload.CategoryDTO;
import com.juliodev.ecommerce.payload.CategoryResponse;
import com.juliodev.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageRequest);
        List<Category> categories = categoryPage.getContent();

        if(categories.isEmpty()) throw new APIException("There are not categories to shown");

        List<CategoryDTO>categoryDTOs = categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOs);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setLastPage(categoryPage.isLast());

        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        Category categoryRevised = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryRevised != null) throw new APIException(String.format("Category with the name %s already exists", category.getCategoryName()));
        Category categoryCreated = categoryRepository.save(modelMapper.map(category, Category.class));
        return modelMapper.map(categoryCreated, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isEmpty()){
            throw new ResourceNotFoundException("Category", "CategoryId", categoryId);
        }
        categoryRepository.delete(optionalCategory.get());
        return modelMapper.map(optionalCategory.get(), CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(categoryDTO.getCategoryName());
            Category categoryUpdated = categoryRepository.save(existingCategory);
            return modelMapper.map(categoryUpdated, CategoryDTO.class);
        }
        else throw new ResourceNotFoundException("Category", "CategoryId", categoryId);
    }
}
