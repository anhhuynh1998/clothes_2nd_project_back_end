package com.example.clothes_2nd.service.home.userInfoHome;

import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.repository.*;
import com.example.clothes_2nd.service.currentUsername.CurrentUsername;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeByUserResponse;
import com.example.clothes_2nd.service.home.cartDetailHome.response.CartDetailHomeResponse;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeByUserResponse;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeResponse;
import com.example.clothes_2nd.service.home.productHome.response.ProductOfHomeListResponse;
import com.example.clothes_2nd.service.home.userInfoHome.request.UserInfoHomeSaveRequest;
import com.example.clothes_2nd.service.home.userInfoHome.response.UserInfoHomeResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class UserInfoHomeService implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;
    private final CurrentUsername currentUsername = new CurrentUsername();
    public UserInfoHomeResponse findByUserInfo() {
        String email = currentUsername.getCurrentUsername();
            if(email != null){
                var userInfo = userInfoRepository.findUserInfoByEmail(email);
                var result = new UserInfoHomeResponse();
                if(userInfoRepository.existsByEmailIgnoreCase(email)){
                    result = AppUtil.mapper.map(userInfo, UserInfoHomeResponse.class);
                    result.setUsername(userInfo.getUser().getUsername());
                }
                return result;
            }
            return null;
    }

    public UserInfo updateUserInfo (UserInfoHomeSaveRequest request){
        String email = currentUsername.getCurrentUsername();
        if(email != null){
            var userInfo = userInfoRepository.findUserInfoByEmail(email);
            AppUtil.mapper.map(request, userInfo);
            userInfoRepository.save(userInfo);
        }
        return null;
    }

    public List<CartHomeByUserResponse> showCartByUser(){
        String email = currentUsername.getCurrentUsername();
        if (email != null) {
            List<CartHomeByUserResponse> result = new ArrayList<>();
            var userInfo = userInfoRepository.findUserInfoByEmail(email);
            if(userInfoRepository.existsByEmailIgnoreCase(email)){
                for(var cart : cartRepository.findCartByUserInfoId(userInfo.getId())){
                    if(cart.getStatus().getId() != 1L){
                        var cartByUser = AppUtil.mapper.map(cart, CartHomeByUserResponse.class);
                        cartByUser.setStatusId(cart.getStatus().getId());
                        cartByUser.setTotalCart(cart.getTotalPrice().add(cart.getShippingFee()));
                        for (var cartDetail : cartDetailRepository.findCartDetailByCartId(cart.getId())){
                            var cartDetailByUser = AppUtil.mapper.map(cartDetail, CartDetailHomeByUserResponse.class);
                            cartByUser.getCartDetailList().add(cartDetailByUser);
                            var product = productRepository.findById(cartDetail.getProduct().getId());
                            var productDetail = AppUtil.mapper.map(product, ProductOfHomeListResponse.class);
                            productDetail.setImageUrl(product.get().getFiles().size() > 0 ? product.get().getFiles().get(0).getUrl() : "");
                            cartDetailByUser.setProduct(productDetail);
                        }
                        result.add(cartByUser);
                    }
                }
            }
            return  result;
        } else {
            System.out.println("User is not authenticated");
        }
        return null;
    }

    public List<ProductOfHomeListResponse> showProductByUser(){
        String email = currentUsername.getCurrentUsername();
        if (email != null) {
            List<ProductOfHomeListResponse> result = new ArrayList<>();
            var userInfo = userInfoRepository.findUserInfoByEmail(email);
            if(userInfoRepository.existsByEmailIgnoreCase(email)){
                for(var product : productRepository.findProductByUserInfoId(userInfo.getId())){
                    var productByUser = AppUtil.mapper.map(product,ProductOfHomeListResponse.class );
                    productByUser.setImageUrl(product.getFiles().get(0).getUrl());
                    result.add(productByUser);
                }
                return result;
            }
        } else {
            System.out.println("User is not authenticated");
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userInfoRepository.findUserInfoByEmail(username);

        var role = new ArrayList<SimpleGrantedAuthority>();
        role.add(new SimpleGrantedAuthority(user.getUser().getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                passwordEncoder.encode(user.getEmail()),
                role);
    }

}
