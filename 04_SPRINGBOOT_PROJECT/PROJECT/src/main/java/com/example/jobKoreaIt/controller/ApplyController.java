package com.example.jobKoreaIt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/apply")
public class ApplyController {

    @GetMapping("/add")
    public void add(){
        log.info("GET /apply/add... ");
    }

    @PostMapping("/add")
    public void add_post(){

    }
}
