package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String registerFrom(Model model){
        model.addAttribute("registerRequest", new OfferDto());
        log.info("GET /offer/join...");
        return "user/login";
    }

    @PostMapping("/join")
    public String OfferJoin(@Valid OfferDto offerDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            log.info("error!!");
            model.addAttribute("OfferDto", offerDto);
        }
        if(!offerDto.getPassword().equals(offerDto.getRepassword())){
            bindingResult.rejectValue("repassword","password.mismatch", "비밀번호가 일치하지않습니다.");
            log.info("repassword error!");
            return "join";
        }if(bindingResult.hasErrors()){
            model.addAttribute("OfferDto",offerDto);
            log.info("tel error!!");
            return "join";
        }


        return "user/login";
    }


//    @GetMapping("/company/add")
//    public void companyAdd(){}
//    @GetMapping("/company/read")
//    public void companyRead(){}
//    @GetMapping("/company/list")
//    public void companyList(){}
//    @GetMapping("/company/delete")
//    public void compayDelete(){}
//    @GetMapping("/company/update")
//    public void companyUpdate(){}
//


}


