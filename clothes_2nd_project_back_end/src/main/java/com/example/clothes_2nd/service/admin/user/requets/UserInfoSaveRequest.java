package com.example.clothes_2nd.service.admin.user.requets;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoSaveRequest {
    private String username;
    private String password;
    private String avatarId;
    @NotBlank(message = "tên người dùng không được để trống")
    @Size(min = 5,max = 30 ,message = "tên người dùng phải từ 5 đến 30 ký tự.")
    private String fullName;
    private String email;
    @NotBlank(message = "số điện thoại không được để trống ")
    private String phone;
    private String gender;
    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
    private String address;

    public UserInfoSaveRequest(String username, String password, Long avatarId, String fullName, String email, String phone, String gender, String provinceId, String provinceName, String districtId, String districtName, String wardId, String wardName, String address) {
        this.username = username;
        this.password = password;
        this.avatarId = String.valueOf(avatarId);
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
