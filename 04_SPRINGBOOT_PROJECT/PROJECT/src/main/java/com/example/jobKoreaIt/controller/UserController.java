package com.example.jobKoreaIt.controller;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.service.UserServiceImpl;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.service.JobSeekerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private JobSeekerServiceImpl jobSeekerService;

    @Autowired
    private JobOfferServiceImpl jobOfferService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public void login_get(){
        log.info("GET /login...");
    }

    @GetMapping("/user/join")
    public void join_get(){
        log.info("GET /join...");
    }



    @GetMapping("/user/confirm_id")
    public void confirm_get_id(){

    }

    @GetMapping("/user/confirm_pw")
    public void confirm_get_pw(){

    }



    @PostMapping("/user/myinfo")
    public void myinfo(){
        log.info("GET /myinfo...");
    }

}
