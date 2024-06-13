package com.sebsach.electronicwebshop.cart;

import com.sebsach.electronicwebshop.product.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart", produces = "application/json")
@Transactional
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<List<ProductResponse>> addToCart(
            @RequestParam(name = "productId") int productId,
            @RequestParam(name = "quantity") int quantity,
            Authentication authentication
    ) {
        return ResponseEntity.ok(cartService.addToCart(productId, quantity, authentication));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getCart(
            Authentication authentication
    ) {
        return ResponseEntity.ok(cartService.getCartItems(authentication));
    }

}
