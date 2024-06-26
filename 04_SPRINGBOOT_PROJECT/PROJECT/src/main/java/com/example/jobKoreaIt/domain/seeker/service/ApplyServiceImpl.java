package com.example.jobKoreaIt.domain.seeker.service;

import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.ApplyRepository;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApplyServiceImpl {
    @Autowired
    ApplyRepository applyRepository;
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    JobSeekerRepository jobSeekerRepository;

    @Transactional(rollbackFor = Exception.class)
    public Apply apply_add(ApplyDto applyDto, Optional<Resume> resume){
        log.info("ApplyServiceImpl/apply_add..");
        log.info("applyDto.. : "+applyDto);
        log.info("resume : "+resume);

        Apply apply=new Apply();
        //resume에 있는 id를 받아온다.
        Optional<Resume> resume1=resumeRepository.findById(resume.get().getId());
        //JobSeeker에있는 username으로 찾아온다.
        JobSeeker JobSeeker = jobSeekerRepository.findByTel(applyDto.getTell());
        log.info("jobSeeker : "+JobSeeker);

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

    //입사지원서 리스트
    @Transactional(rollbackFor = Exception.class)
    public List<ApplyDto> apply_list() {
        log.info("ApplyServiceImpl/apply_list...!");
        List<Apply> applyList = applyRepository.findAll();
        return applyList.stream()
                .map(this::applyDtoList)
                .collect(Collectors.toList());
    }

    //필요한 정보만을 꺼내서 dto에 넣어준다.
    public ApplyDto applyDtoList(Apply apply){
        log.info("ApplyDtoList...필요한 정보를 꺼내온다");
        List<Apply> apply1 = applyRepository.findAll();
        log.info("apply : "+ apply1);

        ApplyDto dto=new ApplyDto();

        dto.setId(apply.getApply_id());

        JobSeeker jobSeeker=apply.getJobSeeker();
        Resume resume=apply.getResume();
        log.info("jobSeeker : "+jobSeeker);
        log.info("resume : "+resume);

        dto.setName(jobSeeker.getUsername());
        dto.setEmail(resume.getEmail());
        dto.setTell(jobSeeker.getTel());
        dto.setObjective(apply.getObjective());
        return dto;
    }
}
