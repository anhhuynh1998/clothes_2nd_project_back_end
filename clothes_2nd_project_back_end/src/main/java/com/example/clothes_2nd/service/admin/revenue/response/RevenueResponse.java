package com.example.clothes_2nd.service.admin.revenue.response;

import com.example.clothes_2nd.model.Status;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class RevenueResponse {
    private BigDecimal totalPrice;
    private LocalDate orderDate;

    public RevenueResponse(BigDecimal totalPrice, Date orderDate) {
        this.totalPrice = totalPrice;
        this.orderDate = orderDate.toLocalDate();
    }
}
