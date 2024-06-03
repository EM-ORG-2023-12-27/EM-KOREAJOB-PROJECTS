package com.example.jobKoreaIt.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/community")
public class CommunityController {

    @GetMapping("/index")
    public void index(){
        log.info("GET /index.css/index...");
    }
}
