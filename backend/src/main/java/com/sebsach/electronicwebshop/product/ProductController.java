package com.sebsach.electronicwebshop.product;

import com.sebsach.electronicwebshop.BackendApplication;
import com.sebsach.electronicwebshop.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "product", produces = "application/json")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "all")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(productService.findAllProducts(page, size));
    }
    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable(name = "productId") int productId
    ) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addOrUpdateProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(ProductMapper.toProductResponse(productService.add(request)));
    }
}
