package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.seeker.repository.ApplyRepository;
import com.example.jobKoreaIt.domain.offer.repository.RecruitRepository;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplyServiceImpl {
    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private RecruitRepository reCruitRepository;



    @Transactional
    public Apply applyForJob(Long jobSeekerId, Long resumeId, Long recruitId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId).orElseThrow(() -> new RuntimeException("Job Seeker not found"));
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));
        Recruit recruit = reCruitRepository.findById(recruitId).orElseThrow(() -> new RuntimeException("Recruit not found"));

        Apply apply = Apply.builder()
                .jobSeeker(jobSeeker)
                .resume(resume)
                .recruit(recruit)
                .build();

        return applyRepository.save(apply);
    }


}
