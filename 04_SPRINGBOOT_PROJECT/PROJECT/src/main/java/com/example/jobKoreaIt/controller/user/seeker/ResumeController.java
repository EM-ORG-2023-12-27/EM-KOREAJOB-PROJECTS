package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.seeker.dto.JobSeekerDto;
import com.example.jobKoreaIt.domain.seeker.dto.ResumeDto;
import com.example.jobKoreaIt.domain.seeker.dto.ResumeFormDto;
import com.example.jobKoreaIt.domain.seeker.entity.Career;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.CareerRepository;
import com.example.jobKoreaIt.domain.seeker.service.ResumeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/seeker")
public class ResumeController {
    //------------------
    //이력서 CRUD (이동환)
    //------------------

    @Autowired
    private ResumeServiceImpl resumeServiceImpl;


    //이력서 작성---
    @GetMapping("/resume/add")
    public String resume_add_get(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        log.info("GET /resume/add..");
        JobSeekerDto jobSeekerDto = principalDetails.getJobSeekerDto();

        model.addAttribute("jobSeekerDto", jobSeekerDto);
        return "seeker/resume/add"; // return the view name
    }

    @PostMapping("/resume/add")
    public String resume_add_post(@ModelAttribute ResumeFormDto form){
        log.info("POST /resume/add..");
        resumeServiceImpl.resume_add(form);
        log.info("Form : "+form);
        return "redirect:/seeker/resume/list"; // 이력서 추가 후 목록 페이지로 리다이렉트

    }

    //수정 ------------------------------------------------------------

    @Autowired
    CareerRepository careerRepository;
    @GetMapping("/resume/update/{id}")
    public String resume_update_get(@PathVariable("id") long id, Model model) {
        log.info("id : "+id);
        log.info("GET /resume/update..");
        Optional<Resume> resumeOptional = resumeServiceImpl.resume_read(id);


        if (resumeOptional.isPresent()) {
            Resume resume= resumeOptional.get();
            System.out.println("/resume/update/{id} resume : " + resume);

            model.addAttribute("resume", resume);


            //------------------------
            List<Career> list =  careerRepository.findAllByResume(resume);

            System.out.println("Career list ! " + list);
            model.addAttribute("list",list);
            //------------------------

            return "seeker/resume/update"; // 수정 페이지 보여주기
        } else {
            model.addAttribute("notFound", "이력서를 찾을 수 없습니다.");
            return "error"; // 에러 페이지 보여주기
        }
    }

    @PostMapping("/resume/update")
    public String resume_update_post(ResumeFormDto formDto) {
        log.info("formDto : "+formDto);
        Long id=formDto.getResume().getId();
        log.info("formDto.id : "+id);
        // Update the resume
        resumeServiceImpl.resume_update(id, formDto);



        return "redirect:/seeker/resume/list";

    }



    //상세읽기--------------------------------------------------------------
    @GetMapping("/resume/read/{id}")
    public String resume_read_get(@PathVariable("id") Long id, Model model) {
        log.info("GET /resume/read..");

        Optional<Resume> resumeOptional = resumeServiceImpl.resume_read(id);
        if (resumeOptional.isPresent()) {
            Resume resume = resumeOptional.get();
            model.addAttribute("resume", resume);
        } else {
            model.addAttribute("notFound", "이력서를 찾을 수 없습니다.");
        }
        return "seeker/resume/read"; // return the view name
    }



    //이력서 항목 리스트 조회------------------------
    @GetMapping("/resume/list")
    public String resume_list_get(Model model){
        log.info("GET /resume/list..");
        List<ResumeDto> resumesList= resumeServiceImpl.resume_list();
        model.addAttribute("resumeList",resumesList);
        return "seeker/resume/list"; // return the view name
    }



    //이력서 삭제--------------------------------------------
    @GetMapping("/resume/delete/{id}")
    public String resume_get_delete(){
        log.info("Get/resume/delete/..");
        return "redirect:/seeker/resume/list";
    }

    @PostMapping("/resume/delete/{id}")
    public String resume_post_delete(@PathVariable("id")long id){
        log.info("Post/resume/delete...."+id);
        resumeServiceImpl.resume_delete(id);
        return "redirect:/seeker/resume/list";
    }


    @GetMapping("/resume/my")
    public @ResponseBody ResponseEntity<Map<String,Object>> getMayResume(@AuthenticationPrincipal PrincipalDetails principalDetails){
         Map<String,Object> result = resumeServiceImpl.getMyResumes(principalDetails.getJobSeekerDto());
         if(result.get("success").equals("false"))
             return new ResponseEntity<>(result, HttpStatus.BAD_GATEWAY);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
