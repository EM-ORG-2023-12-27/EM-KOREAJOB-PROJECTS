package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.domain.Notification.dto.notificationDto;
import com.example.jobKoreaIt.domain.Notification.entity.NotifiEntity;
import com.example.jobKoreaIt.domain.Notification.service.NotifiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/Notification")
public class NotificationController {

    @Autowired
    NotifiService notifiService;

// 공지사항작성--------------------
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
        notifiService.addNotification(notidto);
        return "redirect:/Notification/list";
    }
//공지사항 전체조회---------------
    @GetMapping("/list")
    public String notification_Get(Model model){
        log.info("GET/notificaiton/....");
        List<notificationDto> notifications=notifiService.notifi_list();
        model.addAttribute("notifications",notifications);
        return "Notification/list";
    }

    //공지상세읽기
    @GetMapping("/read/{id}")
    public String notification_read(@PathVariable("id")Long id ,Model model){
        log.info("GET/notification_read...");
        Optional<NotifiEntity> notification=notifiService.notifi_read(id);

        if(notification.isPresent()){
            NotifiEntity entity=notification.get();
            model.addAttribute("entity",entity);
            log.info("entity : "+entity);
        }else{
            model.addAttribute("notFound","공지사항을 못찾았습니다");
        }
        return "Notification/read";
    }
}
