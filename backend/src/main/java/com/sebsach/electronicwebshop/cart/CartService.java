package com.sebsach.electronicwebshop.cart;

import com.sebsach.electronicwebshop.product.ProductMapper;
import com.sebsach.electronicwebshop.product.ProductResponse;
import com.sebsach.electronicwebshop.user.User;
import com.sebsach.electronicwebshop.product.Product;
import com.sebsach.electronicwebshop.repository.CartRepository;
import com.sebsach.electronicwebshop.repository.ProductRepository;
import com.sebsach.electronicwebshop.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public Cart getCart(Authentication authentication) {
        User user = userService.getCurrentUser(authentication);
        return user.getCart();
    }

    public List<ProductResponse> getCartItems(Authentication authentication) {
        Cart cart = getCart(authentication);

        return cart.getCartItems().stream()
                .map(cartItem -> ProductMapper.toProductResponse(cartItem.getProduct()))
                .toList();
    }

    public List<ProductResponse> addToCart(long productId, int quantity, Authentication authentication) {
        Cart cart = getCart(authentication);
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        cart.addItem(product, quantity);
        return saveCart(cart).getCartItems().stream()
                .map(cartItem -> ProductMapper.toProductResponse(cartItem.getProduct()))
                .toList();

    }

    public Cart removeFromCart(long productId, Authentication authentication) {
        Cart cart = getCart(authentication);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        cart.removeItem(product);
        return saveCart(cart);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
