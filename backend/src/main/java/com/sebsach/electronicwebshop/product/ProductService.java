package com.sebsach.electronicwebshop.product;

import com.sebsach.electronicwebshop.PageResponse;
import com.sebsach.electronicwebshop.repository.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.sebsach.electronicwebshop.dto.User;
import com.sebsach.electronicwebshop.repository.ProducerRepository;
import com.sebsach.electronicwebshop.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
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


    public PageResponse<ProductResponse> findAllProducts(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Product> products = productRepository.findAllProducts(pageable, user.getId());
        List<ProductResponse> booksResponse = products.stream()
                .map(ProductMapper::toProductResponse)
                .toList();

        return new PageResponse<ProductResponse>(
                booksResponse,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }


}
