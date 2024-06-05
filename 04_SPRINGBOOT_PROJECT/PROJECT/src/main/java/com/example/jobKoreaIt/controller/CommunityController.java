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
        log.info("GET /community/index...");
    }

    @GetMapping("/add")
    public void add(){
        log.info("GET /community/add.css...");
    }

    @GetMapping("/list")
    public void list(){
        log.info("GET /community/list...");
    }

    @GetMapping("/read")
    public void read(){
        log.info("GET /community/read...");
    }

    @GetMapping("/update")
    public void update(){
        log.info("GET /community/update...");
    }

}
