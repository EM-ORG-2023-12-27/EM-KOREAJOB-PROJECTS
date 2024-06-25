package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.controller.dto.ApplyFormDto;
import com.example.jobKoreaIt.domain.common.entity.Apply;
import com.example.jobKoreaIt.domain.common.service.ApplyService;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            // 유효성 검사 에러가 있을 경우 다시 입력 폼으로 돌아감
            return "apply/add";
        }

        // 현재 로그인한 사용자의 JobSeekerId를 가져옴 (임시로 1L 사용)
        Long jobSeekerId = 1L; //getCurrentJobSeekerId(authentication);

        // jobSeekerId에 대한 ResumeId를 가져옴 (임시로 2L 사용)
        Long resumeId = 2L; //getResumeIdForJobSeeker(jobSeekerId);

        // ApplyService를 통해 공고 지원 처리
        Apply apply = applyService.applyForJob(jobSeekerId, resumeId, applyForm.getJobTitle(), applyForm.getCompanyName());

        // 리다이렉트 시에 사용할 데이터 전달
        redirectAttributes.addFlashAttribute("applyId", apply.getId());

        return "redirect:/apply/success";
    }

    // 공고 지원 성공 페이지
    @GetMapping("/success")
    public String success(Model model) {
        Long applyId = (Long) model.getAttribute("applyId");
        model.addAttribute("applyId", applyId);
        return "apply/success";
    }

    // 현재 로그인한 사용자의 JobSeekerId 가져오기
    private Long getCurrentJobSeekerId(Authentication authentication) {
        // 구현 필요
        return 1L;  // 임시로 고정된 값 사용
    }

    // JobSeekerId에 대한 ResumeId 가져오기
    private Long getResumeIdForJobSeeker(Long jobSeekerId) {
        // 구현 필요
        return 2L;  // 임시로 고정된 값 사용
    }
}
