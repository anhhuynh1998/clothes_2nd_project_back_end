package com.example.clothes_2nd.controller.rest.authentication.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String jwt;
    private Boolean isAdmin;

}
