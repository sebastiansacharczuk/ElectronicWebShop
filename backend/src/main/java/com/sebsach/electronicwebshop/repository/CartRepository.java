package com.sebsach.electronicwebshop.repository;

import com.sebsach.electronicwebshop.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
