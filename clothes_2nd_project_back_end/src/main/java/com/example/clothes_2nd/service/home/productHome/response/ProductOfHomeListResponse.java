package com.example.clothes_2nd.service.home.productHome.response;

import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.emun.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOfHomeListResponse {
    private Long id;
    private String name;
    private BigDecimal salesPrice;
    private BigDecimal price ;

    private String description;
    private Size size;
    private String imageUrl;
    private String status;
    private Boolean paid;
}
