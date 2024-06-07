package com.example.app.controller;

import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/offer")
@Slf4j
public class OfferController {

    @Autowired
    private JobOfferServiceImpl jobOfferServiceImpl;

    @GetMapping("/join")
    public String registerForm(Model model) {
        model.addAttribute("registerRequest", new OfferDto());
        model.addAttribute("userRequest", new UserDto());
        log.info("GET /offer/join...");
        return "user/login";
    }

    @PostMapping("/join")
    public String offerJoin(@ModelAttribute @Valid OfferDto offerDto,
                            BindingResult bindingResult,
                            Model model)
    {
        log.info("POST /offer/join...offerDto : " + offerDto + "offerBindingResiult : " + bindingResult );



        if (bindingResult.hasFieldErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }

            return "/offer/join";
        }

        boolean isRegistered = jobOfferServiceImpl.memberRegistration(null,offerDto);
        if (!isRegistered) {
            model.addAttribute("registrationError", "회원가입 중 오류가 발생했습니다.");
            return "/user/join";
        }

        return "redirect:/user/login";
    }
}