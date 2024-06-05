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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/seeker")
public class SeekerController {




    @Autowired
    private JobSeekerServiceImpl jobSeekerServiceImpl;

    //RegisterRequest  seeker class
    //------------------
    //이력서 CRUD (이동환)
    //------------------
    @GetMapping("/join")
    public String registerFrom(Model model){
        model.addAttribute("registerRequest", new SeekerDto());
        log.info("GET /seeker/join...");
        return "user/login";
    }

    @PostMapping("/join")
    public String SeekerJoin(@Valid SeekerDto seekerDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            log.info("error!!");
            model.addAttribute("SeekerDto", seekerDto);
        }
        if(!seekerDto.getPassword().equals(seekerDto.getRepassword())){
            bindingResult.rejectValue("repassword","password.mismatch", "비밀번호가 일치하지않습니다.");
            log.info("repassword error!");
            return "join";
        }if(bindingResult.hasErrors()){
            model.addAttribute("SeekerDto",seekerDto);
            log.info("tel error!!");
            return "join";
        }
            return "user/login";
        }

//        return "redirect:user/login";
        }

//    @GetMapping("/join")
//    public void join_get(){
//        log.info("GET /seeker/join...");
//    }
//
//    @PostMapping("/join")
//    public @ResponseBody String join_post(){
//        return null;
//    }
//
//
//    @GetMapping("/resume/add")
//    public void resume_add_get(){
//        log.info("GET /resume/add..");
//    }
//    @GetMapping("/resume/update")
//    public void resume_update_get(){
//        log.info("GET /resume/update..");
//    }
//    @GetMapping("/resume/read")
//    public void resume_read_get(){
//        log.info("GET /resume/read..");
//    }
//    @GetMapping("/resume/list")
//    public void resume_list_get(){
//        log.info("GET /resume/list..");
//    }
//
//    @PostMapping("/resume/add")
//    public void resume_add_post(){
//        log.info("GET /resume/add..");
//    }
//    @PostMapping("/resume/update")
//    public void resume_update_post(){
//        log.info("GET /resume/update..");
//    }
//    @PostMapping("/resume/read")
//    public void resume_read_post(){
//        log.info("GET /resume/read..");
//    }
//    @PostMapping("/resume/list")
//    public void resume_list_post(){
//        log.info("GET /resume/list..");
//    }



