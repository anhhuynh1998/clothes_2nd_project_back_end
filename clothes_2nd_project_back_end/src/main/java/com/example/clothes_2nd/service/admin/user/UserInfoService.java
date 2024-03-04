package com.example.clothes_2nd.service.admin.user;

import com.example.clothes_2nd.model.emun.Role;
import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.LocationRegion;
import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.repository.LocationRegionRepository;
import com.example.clothes_2nd.repository.UserInfoRepository;
import com.example.clothes_2nd.repository.UserRepository;
import com.example.clothes_2nd.service.admin.user.response.UserInfoResponse;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import com.example.clothes_2nd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserInfoService implements IUserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRegionRepository userLocationRepository;

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public Page<UserInfoSaveResponse> getAllUserInfo(String search, Pageable pageable) {
        return userInfoRepository.getAllUserInfo(search, pageable);
    }

    @Override
    public UserInfoSaveResponse create(UserInfoSaveRequest userInfoSaveRequest) {
        String username = userInfoSaveRequest.getUsername();
        String avatarId = userInfoSaveRequest.getAvatarId();
        String email = userInfoSaveRequest.getEmail();
        String gender = userInfoSaveRequest.getGender();
        String provinceId = userInfoSaveRequest.getProvinceId();
        String districtId = userInfoSaveRequest.getDistrictId();
        String wardId = userInfoSaveRequest.getWardId();
        String provinceName = userInfoSaveRequest.getProvinceName();
        String districtName = userInfoSaveRequest.getDistrictName();
        String wardName = userInfoSaveRequest.getWardName();
        String address = userInfoSaveRequest.getAddress();

        if(username == null && avatarId == null && email == null && gender == null && provinceId == null
                && districtId == null && wardId == null  && address == null) {
            User user = new User();
            user.setUsername(null);
            user.setPassword("123123");
            user.setAvatar(null);
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);

            UserInfo userInfo = new UserInfo();
            userInfo.setFullName(userInfoSaveRequest.getFullName());
            userInfo.setEmail(null);
            userInfo.setPhone(userInfoSaveRequest.getPhone());
            userInfo.setGender(null);
            userInfo.setUser(user);
            userInfoRepository.save(userInfo);

            LocationRegion locationRegion = new LocationRegion();
            locationRegion.setProvinceId(null);
            locationRegion.setProvinceName(null);
            locationRegion.setDistrictId(null);
            locationRegion.setDistrictName(null);
            locationRegion.setWardId(null);
            locationRegion.setWardName(null);
            locationRegion.setAddress(null);
            locationRegion.setUserInfo(userInfo);
            userLocationRepository.save(locationRegion);

            UserInfoSaveResponse userInfoSaveResponse = new UserInfoSaveResponse();
            userInfoSaveResponse.setId(userInfo.getId());
            userInfoSaveResponse.setUsername(user.getUsername());
            userInfoSaveResponse.setPassword(user.getPassword());
            userInfoSaveResponse.setAvatarId(userInfoSaveResponse.getAvatarId());
            userInfoSaveResponse.setFullName(userInfo.getFullName());
            userInfoSaveResponse.setEmail(userInfo.getEmail());
            userInfoSaveResponse.setPhone(userInfo.getPhone());
            userInfoSaveResponse.setGender(userInfo.getGender());
            userInfoSaveResponse.setProvinceId(locationRegion.getProvinceId());
            userInfoSaveResponse.setProvinceName(locationRegion.getProvinceName());
            userInfoSaveResponse.setDistrictId(locationRegion.getDistrictId());
            userInfoSaveResponse.setDistrictName(locationRegion.getDistrictName());
            userInfoSaveResponse.setWardId(locationRegion.getWardId());
            userInfoSaveResponse.setWardName(locationRegion.getWardName());
            userInfoSaveResponse.setAddress(locationRegion.getAddress());
            return userInfoSaveResponse;
        }
        else {
            User user = new User();
            user.setUsername(userInfoSaveRequest.getUsername());
            user.setPassword("123123");

            if (!Objects.equals(userInfoSaveRequest.getAvatarId(), "")) {
                user.setAvatar(File.builder().id(Long.valueOf(userInfoSaveRequest.getAvatarId())).build());
            }
            user.setRole(Role.ROLE_USER);

            userRepository.save(user);

            UserInfo userInfo = new UserInfo();
            userInfo.setFullName(userInfoSaveRequest.getFullName());
            userInfo.setEmail(userInfoSaveRequest.getEmail());
            userInfo.setPhone(userInfoSaveRequest.getPhone());
            userInfo.setGender(userInfoSaveRequest.getGender());
            userInfo.setUser(user);
            userInfoRepository.save(userInfo);

            LocationRegion locationRegion = new LocationRegion();
            locationRegion.setProvinceId(userInfoSaveRequest.getProvinceId());
            locationRegion.setProvinceName(userInfoSaveRequest.getProvinceName());
            locationRegion.setDistrictId(userInfoSaveRequest.getDistrictId());
            locationRegion.setDistrictName(userInfoSaveRequest.getDistrictName());
            locationRegion.setWardId(userInfoSaveRequest.getWardId());
            locationRegion.setWardName(userInfoSaveRequest.getWardName());
            locationRegion.setAddress(userInfoSaveRequest.getAddress());
            locationRegion.setUserInfo(userInfo);
            userLocationRepository.save(locationRegion);

            UserInfoSaveResponse userInfoSaveResponse = new UserInfoSaveResponse();
            userInfoSaveResponse.setId(userInfo.getId());
            userInfoSaveResponse.setUsername(user.getUsername());
            userInfoSaveResponse.setPassword(user.getPassword());
            userInfoSaveResponse.setAvatarId(userInfoSaveResponse.getAvatarId());
            userInfoSaveResponse.setFullName(userInfo.getFullName());
            userInfoSaveResponse.setEmail(userInfo.getEmail());
            userInfoSaveResponse.setPhone(userInfo.getPhone());
            userInfoSaveResponse.setGender(userInfo.getGender());
            userInfoSaveResponse.setProvinceId(locationRegion.getProvinceId());
            userInfoSaveResponse.setProvinceName(locationRegion.getProvinceName());
            userInfoSaveResponse.setDistrictId(locationRegion.getDistrictId());
            userInfoSaveResponse.setDistrictName(locationRegion.getDistrictName());
            userInfoSaveResponse.setWardId(locationRegion.getWardId());
            userInfoSaveResponse.setWardName(locationRegion.getWardName());
            userInfoSaveResponse.setAddress(locationRegion.getAddress());
            return userInfoSaveResponse;
        }



    }

    @Override
    public UserInfoSaveResponse edit(Long userId, UserInfoSaveRequest userInfoUpdateRequest) {
        User user = userRepository.findById(userId).orElse(null);
        Optional<UserInfo> userInfoOptional = userInfoRepository.findUserInfoByUserId(userId);

        if (userInfoOptional.isPresent()) {
            assert user != null;
            UserInfo userInfo = userInfoOptional.get();
            if (!Objects.equals(userInfoUpdateRequest.getAvatarId(), ""))
                user.setAvatar(File.builder().id(Long.valueOf(userInfoUpdateRequest.getAvatarId())).build());
            userInfo.setUser(user);
            userInfo.setFullName(userInfoUpdateRequest.getFullName());
            userInfo.setEmail(userInfoUpdateRequest.getEmail());
            userInfo.setPhone(userInfoUpdateRequest.getPhone());
            userInfo.setGender(userInfoUpdateRequest.getGender());
            userInfoRepository.save(userInfo);
        }

        LocationRegion locationRegion = userLocationRepository.getLocationRegionByUserInfoId(userInfoOptional.get().getId());
        if (locationRegion != null) {
            locationRegion.setProvinceId(userInfoUpdateRequest.getProvinceId());
            locationRegion.setProvinceName(userInfoUpdateRequest.getProvinceName());
            locationRegion.setDistrictId(userInfoUpdateRequest.getDistrictId());
            locationRegion.setDistrictName(userInfoUpdateRequest.getDistrictName());
            locationRegion.setWardId(userInfoUpdateRequest.getWardId());
            locationRegion.setWardName(userInfoUpdateRequest.getWardName());
            locationRegion.setAddress(userInfoUpdateRequest.getAddress());
            userLocationRepository.save(locationRegion);
        }


        UserInfoSaveResponse userInfoUpdateResponse = new UserInfoSaveResponse();
        userInfoUpdateResponse.setId(user.getId());
        userInfoUpdateResponse.setUsername(user.getUsername());
        userInfoUpdateResponse.setPassword(user.getPassword());
        userInfoUpdateResponse.setAvatarId(userInfoUpdateRequest.getAvatarId());
        userInfoUpdateResponse.setFullName(userInfoOptional.get().getFullName());
        userInfoUpdateResponse.setEmail(userInfoOptional.get().getEmail());
        userInfoUpdateResponse.setPhone(userInfoOptional.get().getPhone());
        userInfoUpdateResponse.setGender(userInfoOptional.get().getGender());
        userInfoUpdateResponse.setProvinceId(locationRegion.getProvinceId());
        userInfoUpdateResponse.setProvinceName(locationRegion.getProvinceName());
        userInfoUpdateResponse.setDistrictId(locationRegion.getDistrictId());
        userInfoUpdateResponse.setDistrictName(locationRegion.getDistrictName());
        userInfoUpdateResponse.setWardId(locationRegion.getWardId());;
        userInfoUpdateResponse.setWardName(locationRegion.getWardName());
        userInfoUpdateResponse.setAddress(locationRegion.getAddress());
        return userInfoUpdateResponse;
    }

    public UserInfo save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
        return userInfo;
    }

    @Override
    public Optional<UserInfoResponse> getUserById(Long id) {
        var userInfo = userInfoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Source not found")
        );
        var userInfoResponse = new UserInfoResponse();
        userInfoResponse.setUserId(userInfo.getUser().getId());
        if(userInfo.getLocationRegion() != null && userInfo.getLocationRegion().size() != 0){
            LocationRegion locationRegion = userInfo.getLocationRegion().get(0);
            AppUtil.mapper.map(locationRegion, userInfoResponse);
        }
        AppUtil.mapper.map(userInfo, userInfoResponse);
        if(userInfo.getUser().getAvatar() != null){
            var avatar = userInfo.getUser().getAvatar();
            userInfoResponse.setAvatarId(String.valueOf(avatar.getId()));
            userInfoResponse.setAvatarUrl(avatar.getUrl());
        }

        return Optional.of(userInfoResponse);
    }

    @Override
    public List<UserInfoSaveResponse> sortAllListUserInfo(String order, String sortBy, String fullName, String email, String phone, String gender) {
        return null;
    }

    @Override
    public void delete(UserInfo userInfo) {

    }

    @Override
    public void deleteById(Long id) {
        Optional<UserInfoSaveResponse> userInfoSaveResponse = userInfoRepository.getUserInfoById(id);

        if (userInfoSaveResponse.isPresent()) {
            userLocationRepository.deleteByUserInfoId(userInfoSaveResponse.get().getId());
            userInfoRepository.deleteById(id);
            userRepository.deleteById(userInfoSaveResponse.get().getUserId());
        }

    }
    @Override
    public ResponseEntity<?> checkPhoneExist(String phone){
      Boolean userInfoSaveResponse =   userInfoRepository.existsByPhone(phone);
        return ResponseEntity.ok(userInfoSaveResponse);
    }

}
