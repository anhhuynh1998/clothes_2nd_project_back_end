package com.example.clothes_2nd.service.home.userInfoHome.response;

import com.example.clothes_2nd.model.Product;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeByUserResponse;
import com.example.clothes_2nd.service.home.cartHome.response.CartHomeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoHomeResponse {
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private String username ;
}
