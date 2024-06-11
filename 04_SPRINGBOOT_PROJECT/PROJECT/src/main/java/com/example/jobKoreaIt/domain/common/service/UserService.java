package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;

public interface UserService {
    User getUser(UserDto userDto);

    boolean confirmIdPw(String username, String password);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    void saveUser(User user);
}
