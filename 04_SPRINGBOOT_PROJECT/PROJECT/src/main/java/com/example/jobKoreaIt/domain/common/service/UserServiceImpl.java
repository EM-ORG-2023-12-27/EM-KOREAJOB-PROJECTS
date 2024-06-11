package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private JobOfferRepository jobOfferRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override// 단일로만 받게끔 추가
    public User getUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findFirstByUsername(userDto.getUsername());
        return userOptional.orElse(null);
    }

    @Override
    public boolean confirmIdPw(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user!=null){
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        return userOptional.orElse(null);
    }

    @Override//추가
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findFirstByUsername(username);
        return userOptional.orElse(null);
    }

    @Override //추가
    public void saveUser(User user) {
        userRepository.save(user);
    }
}

