package com.sebsach.electronicwebshop.repository;

import com.sebsach.electronicwebshop.dto.User;
import com.sebsach.electronicwebshop.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    void deleteById(Long id);
    Product save(Product product);

    @Query("SELECT product FROM Product product")
    Page<Product> findAllProducts(Pageable pageable, Long userId);
}
