package com.sebsach.electronicwebshop.product;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public static ProductResponse toProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .producer(product.getProducer().getName())
                .build();
    }
}
