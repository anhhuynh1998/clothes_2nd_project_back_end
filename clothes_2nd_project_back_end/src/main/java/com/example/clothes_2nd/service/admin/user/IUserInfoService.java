package com.example.clothes_2nd.service.admin.user;

import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.service.admin.IGeneralService;
import com.example.clothes_2nd.service.admin.user.response.UserInfoResponse;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;


public interface IUserInfoService extends IGeneralService<UserInfo,Long> {

    Page<UserInfoSaveResponse> getAllUserInfo(String search , Pageable pageable);

    UserInfoSaveResponse create(UserInfoSaveRequest userInfoSaveRequest);
    UserInfoSaveResponse edit(Long id, UserInfoSaveRequest userInfoSaveRequest);

    UserInfo save(UserInfo userInfo);

    Optional<UserInfoResponse> getUserById(Long id);



    List<UserInfoSaveResponse> sortAllListUserInfo(String order, String sortBy, String fullName, String email, String phone, String gender);

    ResponseEntity<?> checkPhoneExist(String phone);

}
