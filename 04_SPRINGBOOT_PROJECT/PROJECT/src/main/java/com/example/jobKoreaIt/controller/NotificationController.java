package com.example.jobKoreaIt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/notifi")
public class NotificationController {

    @GetMapping("/list")
    public String notification_Get(){
        log.info("GET/notificaiton/....");
        return "Notification/list";
    }
}
