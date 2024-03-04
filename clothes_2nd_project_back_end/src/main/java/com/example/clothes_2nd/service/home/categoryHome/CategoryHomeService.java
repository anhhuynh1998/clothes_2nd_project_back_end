package com.example.clothes_2nd.service.home.categoryHome;

import com.example.clothes_2nd.service.home.categoryHome.response.CategoryListResponse;
import com.example.clothes_2nd.repository.CategoryRepository;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryHomeService {
    private final CategoryRepository categoryRepository;

    public List<CategoryListResponse> findAll() {
        var categories = categoryRepository.findAll();
        List<CategoryListResponse> result = new ArrayList<>();
        for (var category : categories) {
            if (category.getCategoryParent() == null) {
                CategoryListResponse categoryListResponse = AppUtil.mapper.map(category, CategoryListResponse.class);
                List<CategoryListResponse> children = new ArrayList<>();

                for (var item : categories) {
                    if (item.getCategoryParent() != null && item.getCategoryParent().getId() == categoryListResponse.getId()) {
                        CategoryListResponse cate = AppUtil.mapper.map(item, CategoryListResponse.class);
                        cate.setCategoryChildren(
                                categories.stream().filter(e -> e.getCategoryParent() != null
                                        && Objects.equals(e.getCategoryParent().getId(), item.getId()))
                                        .map(e -> AppUtil.mapper.map(e, CategoryListResponse.class))
                                        .collect(Collectors.toList()));
                        children.add(cate);
                    }
                }
                categoryListResponse.setCategoryChildren(children);
                result.add(categoryListResponse);
            }
        }
            return result;
    }

}
