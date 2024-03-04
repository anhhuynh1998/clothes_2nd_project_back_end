package com.example.clothes_2nd.controller.rest.admin.user;
import com.example.clothes_2nd.repository.UserInfoRepository;
import com.example.clothes_2nd.service.admin.user.requets.UserInfoSaveRequest;
import com.example.clothes_2nd.service.admin.user.IUserInfoService;
import com.example.clothes_2nd.service.admin.user.response.UserInfoResponse;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/userinfo")
public class UserInfoRestController {

    private final IUserInfoService iUserInfoService;

    private final UserInfoRepository userInfoRepository;

    public UserInfoRestController(IUserInfoService iUserInfoService, UserInfoRepository userInfoRepository) {
        this.iUserInfoService = iUserInfoService;
        this.userInfoRepository = userInfoRepository;
    }



    @GetMapping
    public ResponseEntity<?> getAllUserInfo(@RequestParam(defaultValue = "") String search, Pageable pageable) {
        Page<UserInfoSaveResponse> userInfos = iUserInfoService.getAllUserInfo(search, pageable);
        return new ResponseEntity<>(userInfos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUserInfo(@RequestBody @Valid UserInfoSaveRequest userInfo) {
        if (userInfoRepository.existsByPhone(userInfo.getPhone())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserInfoSaveResponse userInfoSaveResponse = iUserInfoService.create(userInfo);
        return new ResponseEntity<>(userInfoSaveResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserInfo(@PathVariable Long id, @RequestBody @Valid UserInfoSaveRequest userInfo) {
        UserInfoSaveResponse updatedUserInfo = iUserInfoService.edit(id, userInfo);
        return new ResponseEntity<>(updatedUserInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserInfo(@PathVariable Long id) {
        iUserInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<UserInfoResponse> userInfoResponse = iUserInfoService.getUserById(id);
        return new ResponseEntity<>(userInfoResponse, HttpStatus.OK);
    }

    @GetMapping("/checkPhone/{phone}")
    public  ResponseEntity<?> checkExistPhoneByUser(@PathVariable String phone) {

        return new  ResponseEntity<>(userInfoRepository.findByPhone(phone),HttpStatus.OK);
    }

}

