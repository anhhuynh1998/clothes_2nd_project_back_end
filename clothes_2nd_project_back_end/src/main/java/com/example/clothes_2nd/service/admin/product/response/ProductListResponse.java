package com.example.clothes_2nd.service.admin.product.response;
import com.example.clothes_2nd.model.emun.Size;
import com.example.clothes_2nd.model.emun.Status;
import com.example.clothes_2nd.service.admin.product.request.SelectOptionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductListResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal salesPrice;
    private LocalDateTime depositDate;
    private Boolean active;
    private List<SelectOptionRequest> files;
    private String category;
    private Long categoryParentId;
    private Long categoryGranParentId;
    private Size size;
    private Status status;
    private String userInfo;
    private Boolean paid;
    private String fullName;
    private String phone;
    private String codeProduct;
}
