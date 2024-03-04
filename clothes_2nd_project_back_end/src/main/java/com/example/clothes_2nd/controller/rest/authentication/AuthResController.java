package com.example.clothes_2nd.controller.rest.authentication;


import com.example.clothes_2nd.config.JwtUtil;

import com.example.clothes_2nd.controller.rest.authentication.response.AuthResponse;
import com.example.clothes_2nd.model.File;
import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.model.UserInfo;
import com.example.clothes_2nd.model.emun.FileType;
import com.example.clothes_2nd.model.emun.Role;
import com.example.clothes_2nd.repository.FileRepository;
import com.example.clothes_2nd.repository.UserInfoRepository;
import com.example.clothes_2nd.repository.UserRepository;
import com.example.clothes_2nd.service.admin.user.requets.UserSaveRequest;
import com.example.clothes_2nd.service.home.userHome.request.LoginGoogleSaveRequest;
import com.example.clothes_2nd.service.home.userHome.request.UserHomeSaveRequest;
import com.example.clothes_2nd.util.AppUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.example.clothes_2nd.model.emun.Role.ROLE_ADMIN;
import static com.example.clothes_2nd.model.emun.Role.ROLE_USER;


@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthResController {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final FileRepository fileRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final String SECRET = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserHomeSaveRequest request){

        if(userRepository.existsByUsernameIgnoreCase(request.getUsername()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên Đăng Nhập Đã Tồn Tại");
        if(userInfoRepository.existsByEmailIgnoreCase(request.getEmail()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email Đã Tồn Tại");
        if(userInfoRepository.existsByPhone(request.getPhone()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Số Điện Thoại Đã Tồn Tại");

        var user = AppUtil.mapper.map(request, User.class);
        user.setRole(ROLE_USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        var userInfo = AppUtil.mapper.map(request, UserInfo.class);
        userInfo.setUser(user);
        userInfoRepository.save(userInfo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserSaveRequest request) {
        var user = userRepository.findByUsername(request.getUsername());
        var userInfo = userInfoRepository.findUserInfoByUserId(user.get().getId());

        if (user.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
                String token = jwtToken(userInfo.get().getEmail());
                AuthResponse authResponse = new AuthResponse();
                authResponse.setJwt(token);
                authResponse.setIsAdmin(user.get().getRole().equals(ROLE_ADMIN));
                return ResponseEntity.ok(authResponse);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên Đăng Nhập Hoặc Mật Khẩu Không Đúng");
    }



    @PostMapping("/loginGoogle")
    public ResponseEntity<?> loginGoogle(@RequestBody LoginGoogleSaveRequest request){
        List<String> roles = new ArrayList<>();
        roles.add(ROLE_USER.toString());
        if (!userInfoRepository.existsByEmailIgnoreCase(request.getEmail())) {
            File file = new File();
            file.setUrl(request.getPicture());
            file.setFileType(FileType.IMAGE);
            fileRepository.save(file);

            User user = new User();
            user.setRole(Role.ROLE_USER);
            user.setUsername(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getEmail()));
            user.setAvatar(file);
            userRepository.save(user);

            UserInfo userInfo = new UserInfo();
            userInfo.setEmail(request.getEmail());
            userInfo.setFullName(request.getName());
            userInfo.setUser(user);
            userInfoRepository.save(userInfo);

        }
        return ResponseEntity.ok(getToken(request.getEmail()));
    }


    private String jwtToken(String username) {
        long expiredTime = 86400000;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getToken(String email){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        email
                )
        );


        return jwtUtil.generateToken(email, ROLE_USER.toString());
    }

}
