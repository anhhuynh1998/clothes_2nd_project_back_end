package com.example.clothes_2nd.service.home.productHome.response;

import com.example.clothes_2nd.model.File;
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
public class ProductDetailHomeResponse {
    private Long id;
    private String name;
    private BigDecimal salesPrice;
    private String description;
    private Size size;
    private List<String> listFile;
    private String status;
}
