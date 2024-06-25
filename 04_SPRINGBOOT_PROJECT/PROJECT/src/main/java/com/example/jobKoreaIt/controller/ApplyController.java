package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.domain.common.dto.ApplyFormDto;
import com.example.jobKoreaIt.domain.common.entity.Apply;
import com.example.jobKoreaIt.domain.common.service.ApplyService;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("applyForm", new ApplyFormDto());  // 빈 폼 객체를 모델에 추가
        return "apply/add";
    }

    @PostMapping("/add")
    public String applyForJob(@ModelAttribute("applyForm") @Valid ApplyFormDto applyForm,
                              BindingResult bindingResult,
                              Authentication authentication,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "apply/add";
        }

        Long jobSeekerId = 1L; //getCurrentJobSeekerId(authentication);

        Long resumeId = 2L; //getResumeIdForJobSeeker(jobSeekerId);

        // ApplyService를 통해 공고 지원 처리
        Apply apply = applyService.applyForJob(jobSeekerId, resumeId, applyForm.getJobTitle(), applyForm.getCompanyName());

        // 리다이렉트 시에 사용할 데이터 전달
        redirectAttributes.addFlashAttribute("applyId", apply.getId());

        return "redirect:/apply/success";
    }

}


