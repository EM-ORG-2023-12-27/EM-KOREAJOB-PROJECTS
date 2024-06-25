package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.RecruitRepository;
import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.service.ApplyServiceImpl;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyServiceImpl applyService;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private RecruitRepository recruitRepository;

    @GetMapping("/add")
    public String add() {
        return "apply/add";
    }

    @PostMapping("/add")
    public String applyForJob(@ModelAttribute("applyForm") @Valid ApplyDto applyDto,
                              BindingResult bindingResult,
                              Authentication authentication,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "apply/add";
        }

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        UserDto userDto = principal.getUserDto();
        User user = User.builder()
                .userid(userDto.getUserid())
                .role(userDto.getRole())
                .createAt(userDto.getCreateAt())
                .password(userDto.getPassword())
                .provider(userDto.getProvider())
                .providerId(userDto.getProviderId()).build();

        JobSeeker jobSeeker = jobSeekerRepository.findByUser(user);

        Resume resume = resumeRepository.findByUser(user);

        Recruit recruit = recruitRepository.findByUser(user);

        Apply apply = applyService.applyForJob(jobSeeker.getId(), resume.getId(), recruit.getRecruit_id());

        redirectAttributes.addFlashAttribute("applyId", apply.getApply_id());

        return "redirect:/";

        //
    }

}


