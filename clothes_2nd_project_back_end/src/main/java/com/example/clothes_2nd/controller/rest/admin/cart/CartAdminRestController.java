package com.example.clothes_2nd.controller.rest.admin.cart;

import com.example.clothes_2nd.model.Cart;
import com.example.clothes_2nd.service.admin.cart.CartService;
import com.example.clothes_2nd.service.admin.cart.request.CartSaveRequest;
import com.example.clothes_2nd.service.admin.cart.response.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/cart")
public class CartAdminRestController {

    private final CartService cartService;

    @GetMapping("/sold")
    public List<CartListResponse> productsSoldDay(){
        return cartService.ProductsSoldDay();
    }

//    doanh thu ngay
    @GetMapping("/percent")
    public CartResponse percentTheDay(){

        Float precent = cartService.PercentTheDay();

        CartResponse precentChange = new CartResponse(precent);
        return precentChange;
    }

//    doanh thu theo quý
    @GetMapping("/quarterly")
    public CartQuarterlyResponse quarterlyRevenue(){
        return  cartService.QuarterlyRevenue();
    }

//    lấy tất cả danh sách cart
    @GetMapping("/list")
    public List<CartAdminResponse> getAllCartAdmin(){
        return  cartService.getAllCartAdmin();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchNameAndPhone(@RequestParam(defaultValue = "") String search,
                                                @RequestParam(defaultValue = "") String statusId,
                                                @PageableDefault(value = 20,sort = "id", direction = Sort.Direction.DESC)
                                                Pageable pageable) {

        Page<CartAdminResponse> cartAdminResponses = cartService.searchNameAndPhone(search,statusId, pageable);
        return new ResponseEntity<>(cartAdminResponses, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateStatusOfCart(@RequestParam("cartId") String cartIdStr, @RequestParam("statusId") String statusIdStr){
        Long cartId = Long.parseLong(cartIdStr);
        Long statusId = Long.parseLong(statusIdStr);
        CartAdminResponse cartAdminResponse = cartService.updateStatus(cartId, statusId);
        return new ResponseEntity<>(cartAdminResponse, HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public List<CartAdminResponse> getStatusById(@PathVariable(required = false) Long id){
        return cartService.getStatusById(id);
    }

    @PostMapping("/checkOutAdmin")
    public ResponseEntity<?> checkOutAdmin (@RequestBody CartSaveRequest request) {
        cartService.checkOutAdmin(request);
        return ResponseEntity.ok(request);
    }

}