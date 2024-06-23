package com.example.jobKoreaIt.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

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
