package com.example.jobKoreaIt.domain.seeker.service;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class JobSeekerServiceImpl {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void addJobSeeker(UserDto userDto, JobSeekerDto jobSeekerDto){

        User user = new User();
        user.setUserid(userDto.getUserid());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_SEEKER");
        user.setCreateAt(LocalDateTime.now());
        userRepository.save(user);

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setUser(user);
        jobSeeker.setZipcode(jobSeekerDto.getZipcode());
        jobSeeker.setTel(jobSeekerDto.getTel());
        jobSeeker.setAddr1(jobSeekerDto.getAddr1());
        jobSeeker.setAddr2(jobSeekerDto.getAddr2());
        jobSeekerRepository.save(jobSeeker);

    }

}
