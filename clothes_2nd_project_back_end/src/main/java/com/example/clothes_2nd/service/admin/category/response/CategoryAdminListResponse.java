package com.example.clothes_2nd.service.admin.category.response;

import com.example.clothes_2nd.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryAdminListResponse {
    private Long id;
    private String name;
    private CategoryAdminListResponse categoryParent;
}
