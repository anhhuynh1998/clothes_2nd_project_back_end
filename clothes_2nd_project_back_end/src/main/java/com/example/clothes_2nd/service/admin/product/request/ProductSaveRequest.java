package com.example.clothes_2nd.service.admin.product.request;

import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.UserInfo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSaveRequest {

    private String name;


    private String description;


    private BigDecimal price;


    private String status;


    private String size;


    private SelectOptionRequest category;


    private List<@Valid SelectOptionRequest> files;
    private Boolean paid;
//    private SelectOptionRequest userInfo;
    private Boolean active;
    private BigDecimal salesPrice;
    private LocalDateTime depositDate;

    private String fullName;
//    private UserInfo userInfo;
    private String phone;
    private String codeProduct;
}
