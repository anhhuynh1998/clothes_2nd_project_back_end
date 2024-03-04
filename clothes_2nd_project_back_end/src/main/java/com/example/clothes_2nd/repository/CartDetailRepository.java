package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    List<CartDetail> findCartDetailByCartId(Long cartId);
    //start, end
    //2023-03-01 -> 2023-03-01
    boolean existsByCart_IdAndProduct_IdAndCart_Status_IdAndQuantity
    (Long cart_id, Long product_id, Long cart_status_id, Long quantity);

}
