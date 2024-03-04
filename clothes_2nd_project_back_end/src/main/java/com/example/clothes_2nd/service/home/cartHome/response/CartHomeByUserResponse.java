package com.example.clothes_2nd.service.home.cartHome.response;

import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeByUserResponse;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartHomeByUserResponse {
    private String name;
    private BigDecimal totalPrice ;
    private BigDecimal totalCart ;
    private LocalDate orderDate ;
    private String phone;
    private BigDecimal shippingFee;
    private Long statusId;
    private List<CartDetailHomeByUserResponse> cartDetailList = new ArrayList<>();
}
