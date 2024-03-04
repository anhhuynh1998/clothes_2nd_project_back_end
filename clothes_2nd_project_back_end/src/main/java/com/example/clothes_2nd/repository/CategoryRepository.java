package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.Category;
import com.example.clothes_2nd.service.admin.category.response.CategoryAdminListResponse;
import com.example.clothes_2nd.service.home.categoryHome.response.CategoryListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryParentIsNull();
    List<Category> findByCategoryParentId(Long parentId);
    @Query("SELECT c FROM Category c WHERE c.categoryParent.id = :parentId")
    List<Category> findNestedCategoriesByParentId(Long parentId);

}
