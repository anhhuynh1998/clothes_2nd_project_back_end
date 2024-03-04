package com.example.clothes_2nd.service.admin.cart.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface CartQuarterlyResponse {

    BigDecimal getQ1();
    BigDecimal getQ2();
    BigDecimal getQ3();
    BigDecimal getQ4();
}
