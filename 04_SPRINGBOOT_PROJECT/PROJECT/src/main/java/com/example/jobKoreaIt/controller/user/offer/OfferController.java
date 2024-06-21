package com.example.jobKoreaIt.controller.user.offer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class OfferController {

    @GetMapping("/offer/join")
    public void join_get(){
        log.info("GET /offer/join...");
    }


    @GetMapping("/company/add.css")
    public void companyAdd(){}
    @GetMapping("/company/read")
    public void companyRead(){}
    @GetMapping("/company/list")
    public void companyList(){}
    @GetMapping("/company/delete.css")
    public void compayDelete(){}
    @GetMapping("/company/update")
    public void companyUpdate(){}

}


