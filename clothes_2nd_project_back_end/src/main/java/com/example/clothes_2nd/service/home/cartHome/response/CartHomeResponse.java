package com.example.clothes_2nd.service.home.cartHome.response;

import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartHomeResponse {
    private BigDecimal total = BigDecimal.ZERO;
    private String username;
    private String phone;
    private List<CartDetailHomeResponse> listCartDetail = new ArrayList<>();
}
