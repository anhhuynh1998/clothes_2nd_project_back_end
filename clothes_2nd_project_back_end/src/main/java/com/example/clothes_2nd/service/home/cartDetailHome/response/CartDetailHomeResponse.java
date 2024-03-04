package com.example.clothes_2nd.service.home.cartDetailHome.response;

import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.service.home.productHome.response.ProductDetailHomeResponse;
import com.example.clothes_2nd.service.home.productHome.response.ProductOfHomeListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetailHomeResponse {
    private Long id;
    private Long quantity;
    private BigDecimal total;
    private ProductDetailHomeResponse product;
}
