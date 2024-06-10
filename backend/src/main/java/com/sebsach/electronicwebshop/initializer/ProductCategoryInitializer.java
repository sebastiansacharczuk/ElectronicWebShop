package com.sebsach.electronicwebshop.initializer;


import com.sebsach.electronicwebshop.product.ProductCategory;
import com.sebsach.electronicwebshop.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductCategoryInitializer {

    private final ProductCategoryRepository repository;

    public void run() {

        List<String> categories = List.of("GAMING CONSOLE", "DESKTOP", "MOBILE PHONE", "LAPTOP", "TELEVISION", "TABLET", "SMART WATCH", "AUDIO", "CAMERA");
        for (String category : categories) {
            if (repository.findByName(category).isEmpty()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setName(category);
                repository.save(productCategory);
            }
        }
    }
}

