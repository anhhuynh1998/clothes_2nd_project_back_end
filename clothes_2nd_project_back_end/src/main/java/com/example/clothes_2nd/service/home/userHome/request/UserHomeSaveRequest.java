package com.example.clothes_2nd.service.home.userHome.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserHomeSaveRequest {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String gender;
}
