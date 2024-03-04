package com.example.clothes_2nd.service.admin.user;

import com.example.clothes_2nd.model.User;
import com.example.clothes_2nd.repository.UserRepository;
import com.example.clothes_2nd.service.admin.user.response.UserInfoSaveResponse;
import com.example.clothes_2nd.service.admin.user.response.UserSaveResponse;
import com.example.clothes_2nd.service.home.productHome.response.ProductOfHomeListResponse;
import com.example.clothes_2nd.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteById(Long id) {

    }

    public Page<UserSaveResponse> countUsers(Pageable pageable){
        return userRepository.countUsers(pageable).map(e -> {
            var result = AppUtil.mapper.map(e, UserSaveResponse.class);
            return result;
        });
    }
}
