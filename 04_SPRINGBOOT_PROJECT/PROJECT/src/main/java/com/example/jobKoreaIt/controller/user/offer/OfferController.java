package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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


    @GetMapping("/company/add.css")
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


