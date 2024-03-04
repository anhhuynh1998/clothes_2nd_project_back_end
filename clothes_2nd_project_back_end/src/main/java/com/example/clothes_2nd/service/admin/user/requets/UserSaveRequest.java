package com.example.clothes_2nd.service.admin.user.requets;

import com.example.clothes_2nd.model.emun.Role;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSaveRequest {
    private String username;
    private String password;
}
