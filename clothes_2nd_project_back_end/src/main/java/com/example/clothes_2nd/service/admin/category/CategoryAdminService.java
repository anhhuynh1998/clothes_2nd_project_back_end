package com.example.clothes_2nd.service.admin.category;

import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.repository.CategoryRepository;
import com.example.clothes_2nd.service.admin.category.response.CategoryAdminListResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryAdminService {
    private final CategoryRepository categoryRepository;

    public List<CategoryAdminListResponse> getAllCategories() {


        return categoryRepository.findAllByCategoryParentIsNull().stream().map(e -> AppUtil.mapper.map(e,CategoryAdminListResponse.class)).collect(Collectors.toList());
    }

    public List<CategoryAdminListResponse> getSubcategories(Long id) {
        return categoryRepository.findByCategoryParentId(id).stream().map(e -> AppUtil.mapper.map(e,CategoryAdminListResponse.class)).collect(Collectors.toList());
    }

    public List<CategoryAdminListResponse> getNestedCategories(Long id) {
        return categoryRepository.findNestedCategoriesByParentId(id).stream().map(e -> AppUtil.mapper.map(e,CategoryAdminListResponse.class)).collect(Collectors.toList());

    }




}
