package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private JobOfferServiceImpl  jobOfferServiceImpl;

    @GetMapping("/join")
    public void join_get(){
        log.info("GET /offer/join...");
    }

    @PostMapping("/join")
    public @ResponseBody String join_post(UserDto userDto, OfferDto offerDto){
        log.info("GET /offer/join..." +userDto );
        log.info("GET /offer/join..." +offerDto);

        //2 유효성 체크(Data)
        if(userDto == null || offerDto == null) {
            log.error(" null error");
            return "DATA NULL.";
        }
        if(userDto.getUsername() == null || userDto.getUsername().isEmpty()){
            log.error("ID EMPTY");
            return "EMPTY ID";
        }
        boolean isJoin =jobOfferServiceImpl.memberRegistration(userDto,offerDto);
        if(!isJoin) {
            log.error("Failed!!");
            return "Failed..";
        }

        log.info("Good " + userDto.getUsername());
        return "redirect:/login";

    }


    @GetMapping("/company/add")
    public void companyAdd(){}
    @GetMapping("/company/read")
    public void companyRead(){}
    @GetMapping("/company/list")
    public void companyList(){}
    @GetMapping("/company/delete")
    public void compayDelete(){}
    @GetMapping("/company/update")
    public void companyUpdate(){}



}


