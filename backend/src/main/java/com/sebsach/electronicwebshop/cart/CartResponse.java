package com.sebsach.electronicwebshop.cart;

import com.sebsach.electronicwebshop.product.ProductResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
public class CartResponse {
    private List<ProductResponse> products;
}
