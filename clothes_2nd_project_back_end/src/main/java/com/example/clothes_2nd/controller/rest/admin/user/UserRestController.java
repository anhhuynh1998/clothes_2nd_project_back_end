package com.example.clothes_2nd.controller.rest.admin.user;

import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.service.admin.user.IUserService;
import com.example.clothes_2nd.service.admin.user.UserService;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import com.example.clothes_2nd.service.admin.user.response.UserSaveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin
public class UserRestController {

    private  final IUserService iUserService;
    private final UserService userService;

    public UserRestController(IUserService iUserService, UserService userService) {
        this.iUserService = iUserService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = iUserService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/count")
    public Page<UserSaveResponse> countUsers(Pageable pageable){
        return userService.countUsers(pageable);
    }

}
