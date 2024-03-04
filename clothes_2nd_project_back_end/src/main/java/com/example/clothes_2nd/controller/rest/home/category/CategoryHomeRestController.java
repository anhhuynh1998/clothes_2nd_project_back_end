package com.example.clothes_2nd.controller.rest.home.category;

import com.example.clothes_2nd.service.home.categoryHome.response.CategoryListResponse;
import com.example.clothes_2nd.service.home.categoryHome.CategoryHomeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/home/categories")
public class CategoryHomeRestController {
    private final CategoryHomeService categoryService;

    @GetMapping
    public List<CategoryListResponse> findAll(){
        return categoryService.findAll();
    }
}
