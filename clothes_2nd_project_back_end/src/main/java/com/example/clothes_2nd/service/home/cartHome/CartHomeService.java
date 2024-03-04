package com.example.clothes_2nd.service.home.cartHome;
import com.example.clothes_2nd.model.*;
import com.example.clothes_2nd.repository.*;
import com.example.clothes_2nd.service.currentUsername.CurrentUsername;
import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailNotLoginSaveRequest;
import com.example.clothes_2nd.service.home.cartDetailHome.request.CartDetailSaveRequest;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import com.example.clothes_2nd.service.home.cartHome.request.CartSaveRequest;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeResponse;
import com.example.clothes_2nd.service.home.productHome.response.ProductDetailHomeResponse;


import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Transactional
public class CartHomeService {
    private final LocationRegionRepository locationRegionRepository;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartDetailRepository cartDetailRepository;
    private final StatusRepository statusRepository;
    private final CurrentUsername currentUsername = new CurrentUsername();
    public Cart checkOut(CartSaveRequest request) {
        String email = currentUsername.getCurrentUsername();
        if(email != null){
            Cart cart = cartRepository.findByUserInfo_EmailAndStatus_Id(email, 1L)
                    .orElseThrow(() -> new RuntimeException("Chưa có cart"));
            AppUtil.mapper.map(request, cart);
            LocationRegion locationRegion = request.getLocationRegion();
            locationRegion.setUserInfo(cart.getUserInfo());
            locationRegionRepository.save(locationRegion);
            List<CartDetail> cartDetails = cartDetailRepository.findCartDetailByCartId(cart.getId());
            for (CartDetail item : cartDetails) {
                item.setQuantity(0L);
                cartDetailRepository.save(item);
                var optionalProduct = productRepository.findById(item.getProduct().getId());
                Product product = optionalProduct.get();
                product.setPaid(true);
                productRepository.save(product);
            }
            cart.setTotalPrice(request.getTotalPrice());
            cart.setStatus(new Status(2L));
            cart.setLocationRegion(locationRegion);
            cartRepository.save(cart);
            return cart;
        }
    return null;
    }

    public Cart addToCart(CartDetailSaveRequest request) {
        String email = currentUsername.getCurrentUsername();
        if(email != null){
            var product = productRepository.findById(request.getId());
            var userInfo = userInfoRepository.findUserInfoByEmail(email);

            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(product.get());
            cartDetail.setQuantity(1L);
            cartDetail.setPrice(product.orElseThrow().getSalesPrice());
            cartDetail.setTotal(cartDetail.getPrice());

            Cart cart = cartRepository.findByUserInfo_EmailAndStatus_Id(email, 1L)
                    .orElseGet(() -> {
                        Cart newCart = new Cart();
                        cartDetail.setCart(newCart);
                        newCart.setUserInfo(userInfo);
                        newCart.setStatus(statusRepository.findAll().get(0));
                        List<CartDetail> cartDetails = new ArrayList<>();
                        cartDetails.add(cartDetail);
                        newCart.setTotalPrice(cartDetail.getTotal());
                        newCart.setCartDetails(cartDetails);
                        newCart = cartRepository.save(newCart);
                        return newCart;
                    });
            var check = cartDetailRepository.existsByCart_IdAndProduct_IdAndCart_Status_IdAndQuantity(cart.getId(),
                    product.get().getId(), cart.getStatus().getId(), cartDetail.getQuantity());
            if (check) {
                return null;
            } else {
                cartDetail.setCart(cart);
                cartDetailRepository.save(cartDetail);
                cart.getCartDetails().add(cartDetail);
                cartRepository.save(cart);
                return cart;
            }
        }
    return null;
    }

    public CartHomeResponse showCartDetailsNotLogin(CartDetailNotLoginSaveRequest request) {
        var result = new CartHomeResponse();
        for (var id : request.getProductIdList()) {
            var cartDetail = new CartDetailHomeResponse();
            var product = productRepository.findById(id).get();
            cartDetail.setQuantity(1L);
            cartDetail.setTotal(product.getSalesPrice());

            ProductDetailHomeResponse productDetailHome = AppUtil.mapper.map(product, ProductDetailHomeResponse.class);
            productDetailHome.setListFile(product.getFiles().stream().map(e -> e.getUrl()).collect(Collectors.toList()));

            cartDetail.setProduct(productDetailHome);
            result.getListCartDetail().add(cartDetail);
        }
        return result;
    }

    public void checkOutNotLogin (CartSaveRequest request) {
            var status = statusRepository.findById(2L);
            Cart cart = AppUtil.mapper.map(request, Cart.class);
            cart.setStatus(status.get());
            cart = cartRepository.save(cart);
            List<CartDetail> cartDetails = new ArrayList<>();
            for(var productId : request.getProductIds()){
                var product = productRepository.findById(productId);
                var cartDetail = AppUtil.mapper.map(product, CartDetail.class);
                cartDetail.setProduct(product.get());
                cartDetail.getProduct().setPaid(true);
                cartDetail.setCart(cart);
                cartDetail.setQuantity(1L);
                cartDetail.setTotal(product.get().getSalesPrice());
                cartDetails.add(cartDetail);
            }
            cartDetailRepository.saveAll(cartDetails);
    }

    public CartHomeResponse findAllByUser() {
        String email = currentUsername.getCurrentUsername();
        if(email != null){
            var result = new CartHomeResponse();
            Cart cart = cartRepository.findByUserInfo_EmailAndStatus_Id(email, 1L).orElse(new Cart());
            if (cart.getCartDetails() == null || cart.getCartDetails().size() == 0) {
                return result;
            }
            for (var cartDetail : cart.getCartDetails()) {
                if (cartDetail.getQuantity() != 0 && !cartDetail.getProduct().getPaid()) {
                    var productDetail = AppUtil.mapper.map(cartDetail, CartDetailHomeResponse.class);
                    productDetail.getProduct().setListFile(cartDetail.getProduct().getFiles().stream().map(File::getUrl).collect(Collectors.toList()));
                    result.getListCartDetail().add(productDetail);
                }
                else {
                    cartDetail.setQuantity(0L);
                    cartDetailRepository.save(cartDetail);
                }
            }
            result.setUsername(cart.getUserInfo().getUser().getUsername());
            result.setPhone(cart.getUserInfo().getPhone());
            return result;
        }
        return null;
    }

    public CartHomeResponse removeItem(Long id) {
        CurrentUsername currentUsername = new CurrentUsername();
        String email = currentUsername.getCurrentUsername();
        if(email != null){
            var result = new CartHomeResponse();
            Cart cart = cartRepository.findByUserInfo_EmailAndStatus_Id(email, 1L).orElse(new Cart());
            if (cart.getCartDetails() == null || cart.getCartDetails().size() == 0) {
                return result;
            }
            for (var cartDetail : cart.getCartDetails()) {
                if (cartDetail.getProduct().getId() == id) {
                    cartDetail.setQuantity(0L);
                }else {
                    if(cartDetail.getQuantity() != 0) {
                        var productDetail = AppUtil.mapper.map(cartDetail, CartDetailHomeResponse.class);
                        productDetail.getProduct().setListFile(cartDetail.getProduct().getFiles()
                                .stream().map(File::getUrl).collect(Collectors.toList()));
                        result.getListCartDetail().add(productDetail);
                    }
                }
            }
            return result;
        }
       return null;
    }



}