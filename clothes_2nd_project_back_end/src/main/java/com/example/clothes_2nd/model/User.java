package com.example.clothes_2nd.model;

import com.example.clothes_2nd.model.emun.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    private File avatar;


    public UserInfo getUserInfo() {
        return null;
    }
}
