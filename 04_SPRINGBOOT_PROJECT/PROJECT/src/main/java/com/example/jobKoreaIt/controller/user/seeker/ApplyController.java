package com.example.jobKoreaIt.controller.user.seeker;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.seeker.dto.ApplyDto;
import com.example.jobKoreaIt.domain.seeker.entity.Apply;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import com.example.jobKoreaIt.domain.seeker.service.ApplyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j

public class ApplyController {

    @Autowired
    ResumeRepository resumeRepository;

    private final ApplyServiceImpl applyService;  // ApplyService 필드 추가

    @Autowired  // 의존성 주입을 위한 어노테이션
    public ApplyController(ApplyServiceImpl applyService) {
        this.applyService = applyService;
    }

    //-----------------------------------------------
    //RENEW
    //-----------------------------------------------
    //입사지원서 작성
    @GetMapping("/apply/add")
    public @ResponseBody ResponseEntity<String> Apply_get(
            @RequestParam("recruit_id") Long recruit_id,
            @RequestParam("resume_id") Long resume_id){
        log.info("GET/Apply/add/...resume_id"+ resume_id);

        boolean isOk = applyService.apply_add(recruit_id,resume_id);

        if(isOk)
            return new ResponseEntity<>("Success", HttpStatus.OK);

        return new ResponseEntity<>("Success",HttpStatus.BAD_GATEWAY);
    }


    @GetMapping("/apply/offer/list")
    public @ResponseBody ResponseEntity<List<Apply>> offer_list(@RequestParam("recruit_id") Long recruit_id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        //회사
        List<Apply> list =  applyService.getOfferApply(recruit_id);


        return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @GetMapping("/apply/seeker/list")
    public void seeker_list(){

        //회사

        return ;
    }

//    @PostMapping("/apply/add")
//    public String Apply_Post(Model model,ApplyDto applyDto){
//        log.info("Post/Apply/add...");
//        log.info("applyDto : "+applyDto);
//        Optional<Resume> resume=resumeRepository.findById(applyDto.getResume().getId());
//        log.info("resume : "+resume);
//
//        applyService.apply_add(applyDto,resume);
//
//        model.addAttribute("applyDto",applyDto);
//        model.addAttribute("resume",resume);
//        return "redirect:seeker/apply/list";
//    }



    //내 입사지원서 목록보기
    @GetMapping("/apply/list")
    public String Apply_get_list(Model model){
//        log.info("GET/apply/list...");
//        List<ApplyDto> applyDtoList =applyService.apply_list();
//        model.addAttribute("applyDtoList",applyDtoList);
//        return "seeker/apply/list";
        return "";
    }

}
