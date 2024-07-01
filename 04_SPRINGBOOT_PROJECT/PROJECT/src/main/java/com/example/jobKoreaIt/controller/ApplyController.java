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
import com.example.jobKoreaIt.domain.seeker.service.ResumeServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
@Slf4j
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyServiceImpl applyService;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    RecruitRepository recruitRepository;

    private ResumeServiceImpl resumeServiceImpl;

    @Autowired
    public ApplyController(ResumeServiceImpl resumeService) {
        this.resumeServiceImpl = resumeService;
    }

    @GetMapping("/add")
    public String add(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        UserDto userDto = principal.getUserDto();
        if (userDto == null) {
            return "/login";
        } else {
            return "/apply/add";
        }
    }

    @PostMapping("/add")
    public String add_post(@ModelAttribute ApplyDto applyDto, Model model) {
//        System.out.println("applyDto = " + applyDto + ", bindingResult = " + bindingResult + ", model = " + model);

//        if (bindingResult.hasErrors()) {
//            for(FieldError error  : bindingResult.getFieldErrors()) {
//            log.info(error.getField()+ " : " + error.getDefaultMessage());
//            model.addAttribute(error.getField(), error.getDefaultMessage());
//            }
//            return "/apply/add";
//        }
        System.out.println(applyDto);
//        Optional<Resume> resume = resumeRepository.findById(applyDto.getResume().getId());
//        System.out.println("resume : " + resume);
//
//        Recruit recruit = recruitRepository.findByTitle(applyDto.getRecruit().getTitle());
//        System.out.println("recruit : " + recruit);
//        applyService.apply_add(applyDto, resume, recruit);
//
//        List<Resume> resumeList = resumeServiceImpl.getAllResumes(applyDto.getId()); // 모든 이력서 목록 조회
//        System.out.println("resumeList : " + resumeList);
//        for (Resume e : resumeList) {
//            System.out.println(e);
//        }
//
//        model.addAttribute("applyDto", applyDto);
//        model.addAttribute("resume", resume);
//        model.addAttribute("resumeList", resumeList); // 이력서 목록을 모델에 추가
        return "redirect:/apply/list";
    }


    @GetMapping("list")
    public String Apply_get_list(Model model) {
        List<ApplyDto> applyDtoList = applyService.apply_list();
        model.addAttribute("applyDtoList", applyDtoList);
        return "apply/list";
    }


}


