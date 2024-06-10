package com.sebsach.electronicwebshop.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String producer;
}