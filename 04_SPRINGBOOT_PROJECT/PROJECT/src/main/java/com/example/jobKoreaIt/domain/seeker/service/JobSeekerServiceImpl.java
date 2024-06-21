package com.example.jobKoreaIt.domain.seeker.service;


import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
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
    public void addJobSeeker(JobSeekerDto jobSeekerDto){

        //PW vs RE PW


        jobSeekerRepository.save(null);
    }

}
