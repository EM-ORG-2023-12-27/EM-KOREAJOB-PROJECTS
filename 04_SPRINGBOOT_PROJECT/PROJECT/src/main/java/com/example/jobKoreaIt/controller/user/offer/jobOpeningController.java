package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.offer.service.jobopeningServicelmpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/offer")
public class jobOpeningController {

    @Autowired
    private JobOfferServiceImpl jobOfferService;

    @Autowired
    private jobopeningServicelmpl jobopeningServicelmpl;


    @GetMapping("/jobopening/add")
    public void jobadd() {
        log.info("채용공고 등록...");
    }

    @PostMapping("/jobopening/add")
    public  String jobaddPost(RecruitDto recruitDto, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes rttr) {
        log.info("채용공고 등록중..." + recruitDto);
        JobOfferDto jobOfferDto =  principalDetails.getJobOfferDto();
        jobopeningServicelmpl.jobopenadd(recruitDto,jobOfferDto);

        rttr.addFlashAttribute("message","채용공고 등록완료");
        return "redirect:/offer/jobopening/list";
    }
    @GetMapping("/jobopening/list")
    public void myRecruit(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model){
        log.info("GET /seeker/jobopending/list.." + principalDetails);

        List<Recruit> list = jobopeningServicelmpl.getMyRecruit(principalDetails.getJobOfferDto());

        System.out.println(list);
        model.addAttribute("list",list);



    }


    @GetMapping("/jobopening/read")
    public void jobread(@RequestParam("id") Long id,Model model) {
        log.info("채용공고 조회...");

       Recruit recruit =   jobopeningServicelmpl.getMyRecruitOne(id);
       model.addAttribute("recruit",recruit);

    }

    @GetMapping("/jobopening/delete")
    public void jobdelete() {
        log.info("채용공고 삭제...");
    }

    @PostMapping("/jobopening/delete")
    public String jobdelete(@RequestParam("id") Long id) {
        log.info("채용공고 삭제중...");
        jobopeningServicelmpl.jobopenRemove(id);
        return "redirect:/offer/jobopening/read";
    }

    @GetMapping("/jobopening/update")
    public void jobupdate() {
        log.info("채용공고 수정...");
    }
    
    @PostMapping("/jobopening/update")
    public void jobupdatePost(RecruitDto dto) {
        log.info("채용공고 수정...");

        jobopeningServicelmpl.jobopenupdate(dto);
    }
    
}
