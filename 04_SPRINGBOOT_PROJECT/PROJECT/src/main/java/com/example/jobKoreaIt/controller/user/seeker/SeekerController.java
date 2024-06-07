package com.example.jobKoreaIt.controller.user.seeker;


import com.example.jobKoreaIt.domain.seeker.service.JobSeekerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/seeker")
public class SeekerController {

    //------------------
    //이력서 CRUD (이동환)
    //------------------
    @Autowired
    private JobSeekerServiceImpl jobSeekerServiceImpl;

    @GetMapping("/join")
    public void join_get(){
        log.info("GET /seeker/join...");
    }


    @GetMapping("/resume/add.css")
    public void resume_add_get(){
        log.info("GET /resume/add.css..");
    }
    @GetMapping("/resume/update")
    public void resume_update_get(){
        log.info("GET /resume/update..");
    }
    @GetMapping("/resume/read")
    public void resume_read_get(){
        log.info("GET /resume/read..");
    }
    @GetMapping("/resume/list")
    public void resume_list_get(){
        log.info("GET /resume/list..");
    }

    @PostMapping("/resume/add.css")
    public void resume_add_post(){
        log.info("GET /resume/add.css..");
    }
    @PostMapping("/resume/update")
    public void resume_update_post(){
        log.info("GET /resume/update..");
    }
    @PostMapping("/resume/read")
    public void resume_read_post(){
        log.info("GET /resume/read..");
    }
    @PostMapping("/resume/list")
    public void resume_list_post(){
        log.info("GET /resume/list..");
    }


}
