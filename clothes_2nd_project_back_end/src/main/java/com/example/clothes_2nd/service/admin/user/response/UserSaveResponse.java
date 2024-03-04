package com.example.clothes_2nd.service.admin.user.response;

import com.example.clothes_2nd.model.emun.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSaveResponse {
    private Long id ;
    private String username;
    private String password;
    private Role role;

}
