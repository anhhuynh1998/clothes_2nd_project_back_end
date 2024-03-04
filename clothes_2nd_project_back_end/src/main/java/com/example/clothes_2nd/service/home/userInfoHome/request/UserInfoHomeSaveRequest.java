package com.example.clothes_2nd.service.home.userInfoHome.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoHomeSaveRequest {
    private String email;
    private String fullName;
    private String phone;
    private String gender;
}
