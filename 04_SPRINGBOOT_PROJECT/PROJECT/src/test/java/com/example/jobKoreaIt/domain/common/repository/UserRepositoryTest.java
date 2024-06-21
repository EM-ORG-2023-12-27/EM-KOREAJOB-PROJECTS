package com.example.jobKoreaIt.domain.common.repository;

import com.example.jobKoreaIt.domain.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void t1(){

            Optional<User> userOptional = userRepository.findById("user1");
            if(!userOptional.isEmpty()){
                System.out.println(userOptional.get());
            }

    }

}