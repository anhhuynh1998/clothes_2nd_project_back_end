package com.example.clothes_2nd.service.home.cartDetailHome.request;

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
public class CartDetailNotLoginSaveRequest {
    private List<Long> productIdList = new ArrayList<>();
}
