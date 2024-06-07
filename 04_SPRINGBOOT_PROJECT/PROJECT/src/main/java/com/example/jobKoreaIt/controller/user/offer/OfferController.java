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
    public String offerJoin(@ModelAttribute @Valid OfferDto offerDto, BindingResult offerBindingResult,
                            @ModelAttribute @Valid UserDto userDto, BindingResult userBindingResult, Model model) {
        log.info("POST /offer/join..offerDto : " + offerDto + " offerBindingResult : " + offerBindingResult);
        log.info("POST /offer/join..userDto : " + userDto + " userBindingResult : " + userBindingResult);

        if (offerBindingResult.hasFieldErrors() || userBindingResult.hasFieldErrors()) {
            for (FieldError error : offerBindingResult.getFieldErrors()) {
                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            for (FieldError error : userBindingResult.getFieldErrors()) {
                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "join";
        }

        if (!offerDto.getPassword().equals(offerDto.getRepassword())) {
            offerBindingResult.rejectValue("repassword", "password.mismatch", "비밀번호가 일치하지 않습니다.");
            log.info("repassword error!");
            return "join";
        }

        boolean isRegistered = jobOfferServiceImpl.memberRegistration(userDto, offerDto);
        if (!isRegistered) {
            model.addAttribute("registrationError", "회원가입 중 오류가 발생했습니다.");
            return "join";
        }

        return "redirect:/user/login";
    }
}