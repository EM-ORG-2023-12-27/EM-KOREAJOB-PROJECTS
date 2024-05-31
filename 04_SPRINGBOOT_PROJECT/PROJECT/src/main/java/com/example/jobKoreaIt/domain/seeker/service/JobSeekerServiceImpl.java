package com.example.jobKoreaIt.domain.seeker.service;


import com.example.jobKoreaIt.domain.common.repository.SeekerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j

public class JobSeekerServiceImpl {

    @Autowired
    private SeekerRepository seekerRepository;

    @Transactional(rollbackFor = Exception.class)
    public void function(){
        log.info("TEST...");
    }
}
