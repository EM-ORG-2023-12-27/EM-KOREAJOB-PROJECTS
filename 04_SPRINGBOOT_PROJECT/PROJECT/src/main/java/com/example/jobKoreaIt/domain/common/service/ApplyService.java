package com.example.jobKoreaIt.domain.common.service;

import com.example.jobKoreaIt.domain.common.entity.Apply;
import com.example.jobKoreaIt.domain.common.repository.ApplyRepository;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ApplyService {
    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional
    public Apply applyForJob(Long jobSeekerId, Long resumeId, String jobTitle, String companyName) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).orElseThrow(() -> new RuntimeException("Job Seeker not found"));
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("Resume not found"));

        Apply apply = Apply.builder()
                .jobSeeker(jobSeeker)
                .resume(resume)
                .jobTitle(jobTitle)
                .companyName(companyName)
                .status("Applied")
                .build();

        return applyRepository.save(apply);
    }

}
