package com.example.clothes_2nd.service.home.productHome.request;

import com.example.clothes_2nd.model.emun.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFilterRequest {
    private String size;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private Long categoryId;
    private String search = "";
    private List<Size> sizes;
}
