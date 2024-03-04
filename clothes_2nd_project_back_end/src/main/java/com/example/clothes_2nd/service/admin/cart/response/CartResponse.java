package com.example.clothes_2nd.service.admin.cart.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CartResponse {
    private Float totalPrice;
    public CartResponse(Float totalPrice){
        this.totalPrice = totalPrice;
    }

}
