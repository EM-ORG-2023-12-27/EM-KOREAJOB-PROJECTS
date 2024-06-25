package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.domain.common.dto.ApplyDto;
import com.example.jobKoreaIt.domain.common.entity.Apply;
import com.example.jobKoreaIt.domain.common.service.ApplyService;
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
    private ApplyService applyService;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private ResumeRepository resumeRepository;

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

        Long jobSeekerId = 1L;

        Long resumeId = 2L;

        Apply apply = applyService.applyForJob(applyDto.getUserid(),applyDto.getRecruit_id());

        redirectAttributes.addFlashAttribute("applyId", apply.getId());

        return "redirect:/";
    }

}


