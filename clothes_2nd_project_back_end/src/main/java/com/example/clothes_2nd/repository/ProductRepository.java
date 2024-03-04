package com.example.clothes_2nd.repository;
import com.example.clothes_2nd.service.admin.product.response.ProductListResponse;
import com.example.clothes_2nd.service.home.productHome.request.ProductFilterRequest;
import com.example.clothes_2nd.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
@Query(value = "SELECT p FROM Product p  WHERE " +
        "(:#{#request.sizes} is null or  p.size in :#{#request.sizes}) " +
        "AND (COALESCE(:#{#request.categoryId}, p.category.id) = p.category.id) " +
        "AND (COALESCE(:#{#request.priceMin}, p.salesPrice) <= p.salesPrice) " +
        "AND (COALESCE(:#{#request.priceMax}, p.salesPrice) >= p.salesPrice) " +
        "AND p.name LIKE CONCAT('%', :#{#request.search}, '%') " +
        "AND p.paid = false AND SIZE(p.files) > 0")
Page<Product> filterProduct(@Param("request") ProductFilterRequest request, Pageable pageable);


    @Query("SELECT p FROM Product p WHERE " +
            "(p.name LIKE %:search% OR " +
            "p.codeProduct LIKE %:search%  OR " +
            "p.description LIKE %:search%  OR " +
            "p.category.name LIKE %:search% ) ")

    Page<Product> searchEverything(
            @Param("search") String search,
            Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.paid = false ")
    Page<Product> findAllProduct(Pageable pageable);


//    đếm số lượng sản phẩm
    @Query(value = "select COUNT(p) from Product as p where p.paid = false")
    long countProduct();

    //lấy sản phẩm theo mã
    @Query(value = "select p from Product  p where p.codeProduct = :search")
    Product getProductsByCodeProduct(@Param("search") String search);

    @Query(value = "select sum(p.price) from  Product  p where p.paid = false ")
    BigDecimal checkOutProduct();

   List<Product>  findProductByUserInfoId(Long userInfo_id);
}
