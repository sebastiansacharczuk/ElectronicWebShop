package com.sebsach.electronicwebshop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String producer;
    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }
}
