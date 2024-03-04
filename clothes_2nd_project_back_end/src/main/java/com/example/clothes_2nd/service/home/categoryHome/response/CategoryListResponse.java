package com.example.clothes_2nd.service.home.categoryHome.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryListResponse {
    private Long id;
    private String name;
    private List<CategoryListResponse> categoryChildren;
}
