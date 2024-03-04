package com.example.clothes_2nd.controller.rest.home.cart;

import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailNotLoginSaveRequest;
import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailSaveRequest;
import com.example.clothes_2nd.service.home.cartHome.request.CartSaveRequest;
import com.example.clothes_2nd.service.home.cartHome.CartHomeService;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/home/carts")
public class CartRestController {
    private final CartHomeService cartHomeService;

    @PatchMapping ("/checkOut")
    public ResponseEntity<?> checkOut (@RequestBody CartSaveRequest request){
        cartHomeService.checkOut(request);
        return ResponseEntity.ok(request);
    }
    @PostMapping("/checkOutNotLogin")
    public ResponseEntity<?> checkOutNotLogin (@RequestBody CartSaveRequest request){
        cartHomeService.checkOutNotLogin(request);
        return ResponseEntity.ok(request);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart (@RequestBody CartDetailSaveRequest request){
        cartHomeService.addToCart(request);
        return ResponseEntity.ok(request);
    }
    @PostMapping("/showCartDetailsNotLogin")
    public CartHomeResponse showCartDetailsNotLogin(@RequestBody CartDetailNotLoginSaveRequest request){
        return cartHomeService.showCartDetailsNotLogin(request);
    }

    @GetMapping
    public CartHomeResponse findAllByUser(){
      return  cartHomeService.findAllByUser();
    }

    @DeleteMapping("{id}")
    public CartHomeResponse removeItem(@PathVariable Long id) {
        return cartHomeService.removeItem(id);
    }



}
