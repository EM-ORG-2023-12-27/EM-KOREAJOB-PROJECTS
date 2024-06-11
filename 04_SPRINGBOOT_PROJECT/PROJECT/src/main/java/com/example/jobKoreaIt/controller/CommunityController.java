package com.example.jobKoreaIt.controller;


import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import com.example.jobKoreaIt.domain.common.service.CommunityServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityServiceImpl communityService;

    @GetMapping("/index")
    public void index(){
        log.info("GET /community/index...");
    }

    @GetMapping("/add")
    public void add(){
        log.info("GET /community/add.css...");
    }


    @PostMapping("/add")
    public String add_post(@ModelAttribute  @Valid CommunityDto dto, BindingResult bindingResult, Model model) throws IOException {
        log.info("GET /community/add..." + dto);
        //유효성 검사
        if(bindingResult.hasFieldErrors()) {
            for(FieldError error  : bindingResult.getFieldErrors()) {
                log.info(error.getField()+ " : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "community/add";
        }
        //서비스 실행
        boolean isadded = communityService.addCommunity(dto);
        if(!isadded)
            return "community/add";

        return "redirect:/community/list";
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
