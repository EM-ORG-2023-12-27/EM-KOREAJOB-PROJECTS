package com.example.jobKoreaIt.controller.user.seeker;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import com.example.jobKoreaIt.domain.seeker.service.JobSeekerServiceImpl;
import jakarta.mail.Message;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/seeker")
public class SeekerController {

    @Autowired
    private JobSeekerServiceImpl jobSeekerServiceImpl;

    @GetMapping("/join")
    public String registerForm(Model model) {
        model.addAttribute("registerRequest", new SeekerDto());
        model.addAttribute("userRequest", new UserDto());
        log.info("GET /seeker/join...");
        return "user/login";
    }

    @PostMapping("/join")
    public String seekerJoin(@ModelAttribute @Valid SeekerDto seekerDto, BindingResult seekerBindingResult,
                             @ModelAttribute @Valid UserDto userDto, BindingResult userBindingResult, Model model) {
        log.info("POST /seeker/join..seekerDto : " + seekerDto + " seekerBindingResult : " + seekerBindingResult);
        log.info("POST /seeker/join..userDto : " + userDto + " userBindingResult : " + userBindingResult);

        if (seekerBindingResult.hasFieldErrors() || userBindingResult.hasFieldErrors()) {
            for (FieldError error : seekerBindingResult.getFieldErrors()) {
                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            for (FieldError error : userBindingResult.getFieldErrors()) {
                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "join";
        }

        if (!seekerDto.getPassword().equals(seekerDto.getRepassword())) {
            seekerBindingResult.rejectValue("repassword", "password.mismatch", "비밀번호가 일치하지 않습니다.");
            log.info("repassword error!");
            return "join";
        }


        boolean isRegistered = jobSeekerServiceImpl.memberRegistration(userDto, seekerDto);
        if (!isRegistered) {
            model.addAttribute("registrationError", "회원가입 중 오류가 발생했습니다.");
            return "join";
        }

        return "redirect:/user/login";
    }
}
