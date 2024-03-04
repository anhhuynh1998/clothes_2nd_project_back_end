package com.example.clothes_2nd.service.home.cartDetailHome.request;

import com.example.clothes_2nd.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetailSaveRequest {
    private Long id;
    private Long quantity;
}
