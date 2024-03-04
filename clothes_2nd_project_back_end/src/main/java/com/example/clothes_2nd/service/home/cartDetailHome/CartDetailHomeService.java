package com.example.clothes_2nd.service.home.cartDetailHome;

import com.example.clothes_2nd.model.CartDetail;
import com.example.clothes_2nd.repository.CartDetailRepository;
import com.example.clothes_2nd.repository.CartRepository;
import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailSaveRequest;
import com.example.clothes_2nd.repository.ProductRepository;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class CartDetailHomeService {

    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;

    public CartDetail addToCart(CartDetailSaveRequest request){
        var result = AppUtil.mapper.map(request , CartDetail.class);
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(result.getProduct());
        var products = productRepository.findAll();
                for(var item : products){
                    if(item.getId() == request.getId()) {
                        cartDetail.setQuantity(1L);
                        cartDetail.setTotal(cartDetail.getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
                        cartDetailRepository.save(cartDetail);
                        return cartDetail;
                    }
            }
        return null;
    }
}
