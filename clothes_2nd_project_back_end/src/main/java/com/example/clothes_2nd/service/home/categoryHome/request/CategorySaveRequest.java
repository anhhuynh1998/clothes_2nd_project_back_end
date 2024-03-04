package com.example.clothes_2nd.service.home.categoryHome.request;

import com.example.clothes_2nd.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategorySaveRequest {
    private Long id;
    private String name;
    private Category categoryParentId;
}
