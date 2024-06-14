package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.domain.Notification.dto.notificationDto;
import com.example.jobKoreaIt.domain.Notification.service.NotifiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/notifi")
public class NotificationController {

    @Autowired
    NotifiService notifiService;

    @GetMapping("/list")
    public String notification_Get(){
        log.info("GET/notificaiton/....");
        return "Notification/list";
    }

    @GetMapping("/add")
    public String noticaition_add_Get(Model model){
        log.info("GET/noticaition_add_Get...");
        model.addAttribute("notificationDto",new notificationDto());
        return "Notification/add";
    }

    @PostMapping("/add")
    public String notification_post(@ModelAttribute notificationDto notidto){
        log.info("Post/notification_post...");
        log.info("notidto : "+notidto);
        notifiService.notifi_add(notidto);
        return "redirect:/Notification/list";
    }
}
