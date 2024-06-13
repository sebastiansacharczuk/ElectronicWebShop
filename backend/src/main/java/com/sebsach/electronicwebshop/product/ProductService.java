package com.sebsach.electronicwebshop.product;

import com.sebsach.electronicwebshop.dto.PageResponse;
import com.sebsach.electronicwebshop.repository.ProductCategoryRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.sebsach.electronicwebshop.repository.ProducerRepository;
import com.sebsach.electronicwebshop.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;
    private final ProductCategoryRepository productCategoryRepository;
    @PersistenceContext
    private final EntityManager entityManager;

    public Product add(ProductRequest request) {
        Producer producer = producerRepository.findByName(request.getProducer()).orElseThrow(() -> new BadCredentialsException("Invalid producer name:" + request.getProducer()));
        ProductCategory productCategory = productCategoryRepository.findByName(request.getCategory()).orElseThrow(() -> new BadCredentialsException("Invalid product category name"));

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .producer(producer)
                .productCategory(productCategory)
                .quantity(request.getQuantity())
                .build();
        return productRepository.save(product);
    }

    public PageResponse<ProductResponse> findAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Product> products = productRepository.findAllProducts(pageable);
        List<ProductResponse> productsResponse = products.stream()
                .map(ProductMapper::toProductResponse)
                .toList();

        return new PageResponse<ProductResponse>(
                productsResponse,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }

    public ProductResponse findProductById(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toProductResponse(product);
    }
}
