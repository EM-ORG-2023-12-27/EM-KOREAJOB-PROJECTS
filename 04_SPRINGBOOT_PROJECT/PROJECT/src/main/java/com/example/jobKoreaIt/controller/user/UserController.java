package com.example.jobKoreaIt.controller.user;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/user/login")
    public void login_get(){
        log.info("GET /login...");
    }

    @GetMapping("/user/join")
    public void join_get(){
        log.info("GET /join...");
    }


    @PostMapping("/user/myinfo")
    public void myinfo(){
        log.info("GET /myinfo...");
    }

}
