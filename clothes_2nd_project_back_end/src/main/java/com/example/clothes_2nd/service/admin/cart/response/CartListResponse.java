package com.example.clothes_2nd.service.admin.cart.response;

import com.example.clothes_2nd.model.Status;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartListResponse {
    private Long id;
    private String name;
    private BigDecimal totalPrice;
    private LocalDate orderDate;


}
