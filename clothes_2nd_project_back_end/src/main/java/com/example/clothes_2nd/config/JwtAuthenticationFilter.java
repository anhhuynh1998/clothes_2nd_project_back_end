package com.example.clothes_2nd.config;

import com.example.clothes_2nd.repository.UserInfoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserInfoRepository userInfoRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserInfoRepository userInfoRepository) {
        this.jwtUtil = jwtUtil;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    protected void doFilterInternal(
             HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwt);
        var userInfo = userInfoRepository.findUserInfoByEmail(userEmail);
        var userDetailsUserDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userInfo.getUser().getRole().toString());
                var arr = new ArrayList<GrantedAuthority>();
                arr.add(simpleGrantedAuthority);
                return arr;
            }

            @Override
            public String getPassword() {
                return userEmail;
            }

            @Override
            public String getUsername() {
                return userEmail;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetailsUserDetails,
                null,
                userDetailsUserDetails.getAuthorities()
        );
        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);


        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        //Bearer
        String jwtWithBarer = request.getHeader("Authorization");
        if (jwtWithBarer == null) return null;
        return jwtWithBarer.substring(7);
    }


}
