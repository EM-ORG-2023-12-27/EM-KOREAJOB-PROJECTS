package com.example.jobKoreaIt.controller.user.seeker;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SeekerController {

    @GetMapping("/seeker/join")
    public void join_get(){
        log.info("GET /seeker/join...");
    }
}
