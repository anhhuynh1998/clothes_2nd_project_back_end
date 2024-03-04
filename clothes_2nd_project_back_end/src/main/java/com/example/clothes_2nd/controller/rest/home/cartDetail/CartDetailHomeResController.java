package com.example.clothes_2nd.controller.rest.home.cartDetail;

import com.example.clothes_2nd.model.CartDetail;
import com.example.clothes_2nd.service.home.cartDetailHome.CartDetailHomeService;
import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailSaveRequest;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api/home/cartDetails")
public class CartDetailHomeResController {
    private CartDetailHomeService cartDetailHomeService;

    @PostMapping
    public CartDetail addToCart(@RequestBody CartDetailSaveRequest request){
        return cartDetailHomeService.addToCart(request);
    }
}
