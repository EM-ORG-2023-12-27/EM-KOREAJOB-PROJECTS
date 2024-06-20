package com.example.jobKoreaIt.domain.seeker.service;



import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class JobSeekerServiceImpl {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;



    @Transactional(rollbackFor = Exception.class)
    public void function(){
        log.info("TEST...");
    }


    //------------------
    //이력서 CRUD (이동환)
    //------------------
    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void resume_add(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_list(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_read(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_delete(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_update(){
        log.info("TEST...");
    }



}
