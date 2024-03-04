package com.example.clothes_2nd.service.admin.cart.response;

import com.example.clothes_2nd.model.CartDetail;
import com.example.clothes_2nd.model.LocationRegion;
import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.model.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartAdminResponse {
    private Long id;
    private String name;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private LocalDate orderDate = LocalDate.now();
    private String phone;
    private BigDecimal shippingFee;
    private Status status;
    private LocationRegion locationRegion;
    private List<String> productNames;


}
