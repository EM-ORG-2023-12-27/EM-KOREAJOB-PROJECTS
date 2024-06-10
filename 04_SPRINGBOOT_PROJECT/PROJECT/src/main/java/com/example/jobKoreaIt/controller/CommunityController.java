package com.example.jobKoreaIt.controller;


import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import com.example.jobKoreaIt.domain.common.service.CommunityServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor // ?
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityServiceImpl communityService;

    @GetMapping("/index")
    public void index(){
        log.info("GET /community/index...");
    }

    @GetMapping("/add")
    public String add(){
        log.info("GET /community/add.css...");
        return "add";
    }


    @PostMapping("/add")
    public String add_post(@ModelAttribute CommunityDto communityDto){ //Dto는 dto 로 그냥 ?
        log.info("GET /community/add.css...");
        System.out.println("communityDto = " + communityDto);
        communityService.addCommunity(communityDto);
        return null;

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
