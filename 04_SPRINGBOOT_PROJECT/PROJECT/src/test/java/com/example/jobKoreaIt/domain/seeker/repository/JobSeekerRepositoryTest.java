package com.example.jobKoreaIt.domain.seeker.repository;

import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JobSeekerRepositoryTest {

    @Autowired
    JobSeekerRepository jobSeekerRepository;
    @Test
    public void t1(){
       Optional<JobSeeker> t =jobSeekerRepository.findByUsernameAndTelAndNickname("jwg8910@naver.com","01023753058","정우균");
        System.out.println(t.get());
    }

}