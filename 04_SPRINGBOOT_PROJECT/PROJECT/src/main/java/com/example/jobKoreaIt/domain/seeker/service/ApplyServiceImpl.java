package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional(rollbackFor = Exception.class)
    public Apply apply_add(ApplyDto applyDto, Optional<Resume> resume, Recruit recruit){

        Apply apply=new Apply();

        Optional<Resume> resume1 = resumeRepository.findById(resume.get().getId());
        JobSeeker JobSeeker = jobSeekerRepository.findByTel(applyDto.getTell());
        log.info("jobSeeker : "+JobSeeker);

        apply.setRecruit(recruit);
        apply.setMilitaryService(applyDto.getMilitaryService());
        apply.setNationality(applyDto.getNationality());
        apply.setObjective(applyDto.getObjective());
        apply.setExpectedSalary(applyDto.getExpectedSalary());
        apply.setApply_id(applyDto.getId());
        apply.setResume(resume1.get());
        apply.setJobSeeker(JobSeeker);

        log.info("apply : "+apply);
        return  applyRepository.save(apply);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ApplyDto> apply_list() {
        log.info("ApplyServiceImpl/apply_list...!");
        List<Apply> applyList = applyRepository.findAll();
        return applyList.stream()
                .map(this::applyDtoList)
                .collect(Collectors.toList());
    }

    public ApplyDto applyDtoList(Apply apply){
        log.info("ApplyDtoList...필요한 정보를 꺼내온다");
        List<Apply> apply1 = applyRepository.findAll();
        log.info("apply : "+ apply1);

        ApplyDto dto=new ApplyDto();

        dto.setId(apply.getApply_id());

        JobSeeker jobSeeker=apply.getJobSeeker();
        Resume resume=apply.getResume();
        Recruit recruit=apply.getRecruit();
        log.info("jobSeeker : "+jobSeeker);
        log.info("resume : "+resume);

        dto.setName(jobSeeker.getUsername());
        dto.setEmail(resume.getEmail());
        dto.setTell(jobSeeker.getTel());
        dto.setObjective(apply.getObjective());
        return dto;
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Apply> applyRead(long id){
        log.info("ApplyServiceImpl/applyRead/id... "+id);
        return applyRepository.findById(id);
    }


}
