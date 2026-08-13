package com.juliodev.ecommerce.repositories;

import com.juliodev.ecommerce.model.Category;
import com.juliodev.ecommerce.model.Product;
import com.juliodev.ecommerce.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    //TODO:Revise documentation about Pageable and OrderByPriceAsc usage
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageDetails);
    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);
    Page<Product> findByUser(User user, Pageable pageDetails);
}
