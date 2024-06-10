package com.sebsach.electronicwebshop.repository;

import com.sebsach.electronicwebshop.product.Producer;
import com.sebsach.electronicwebshop.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String name);
}
