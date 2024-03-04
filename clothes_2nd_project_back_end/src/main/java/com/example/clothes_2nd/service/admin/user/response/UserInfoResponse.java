package com.example.clothes_2nd.service.admin.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserInfoResponse {
    private Long id;
    private Long userId;
    private String username;

    private String password;

    private String avatarId;
    private String avatarUrl;

    private String fullName;
    private String email;
    private String phone;
    private String gender;

    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
    private String address;

    public UserInfoResponse(Long id, Long userId, String username, String password, Long avatarId,String avatarUrl, String fullName, String email, String phone, String gender, String provinceId, String provinceName, String districtId, String districtName, String wardId, String wardName, String address) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.avatarId = String.valueOf(avatarId);
        this.avatarUrl = avatarUrl;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

