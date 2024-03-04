package com.example.clothes_2nd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    @OneToMany(mappedBy = "userInfo")
    @JsonIgnore
    private List<LocationRegion> locationRegion;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user ;
    @JsonIgnore
    @OneToMany(mappedBy = "userInfo" , cascade = CascadeType.ALL)
    private List<Cart> cartList;
    @JsonIgnore
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<Product> productList;
}
