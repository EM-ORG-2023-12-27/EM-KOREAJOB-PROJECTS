package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public User getUser(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail());
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
        return userRepository.findByEmail(email);
    }
}

