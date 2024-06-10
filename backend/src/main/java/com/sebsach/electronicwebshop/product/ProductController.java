package com.sebsach.electronicwebshop.product;

import com.sebsach.electronicwebshop.BackendApplication;
import com.sebsach.electronicwebshop.PageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);
    private final ProductService productService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(productService.findAllProducts(page, size, connectedUser));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrUpdateProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.add(request));
    }
}
