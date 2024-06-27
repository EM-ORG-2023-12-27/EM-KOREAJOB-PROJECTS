package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.dto.RecruitFormDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.CompanyRepository;
import com.example.jobKoreaIt.domain.offer.service.RecruitServiceImpl;
import com.example.jobKoreaIt.domain.seeker.repository.RecruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/api/offer/jobopening")
public class RestRecruitController {

    @Autowired
    private RecruitServiceImpl jobOfferServiceImpl;

    @GetMapping("/add")
    public String recruit_add_get(Model model){
        log.info("GET /api/offer/jobopening/add..");
        model.addAttribute("recruitForm", new RecruitFormDto());
        return "offer/jobopening/add";
    }

    @PostMapping("/add")
    public String recruit_add_post(@ModelAttribute RecruitFormDto form){
        log.info("POST /api/offer/jobopening/add..");
        jobOfferServiceImpl.recruit_add(form);
        log.info("Form :" + form);
        return "redirect:/offer/jobopening/list";
    }

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/update/{id}")
    public String recruit_update_get(@PathVariable("id") long id, Model model) {
        log.info("id : " + id);
        log.info("GET /api/offer/jobopening/update..");
        Optional<Recruit> recruitOptional = jobOfferServiceImpl.recruit_read(id);

        if (recruitOptional.isPresent()) {
            Recruit recruit = recruitOptional.get();
            System.out.println("/api/offer/jobopening/update/{id} recruit : " + recruit);

            model.addAttribute("recruit", recruit);

            List<Company> list = companyRepository.findAllByRecruit(recruit);

            System.out.println("Company list " + list);
            model.addAttribute("list", list);

            return "offer/jobopening/update";
        } else {
            model.addAttribute("notFound", "게시글을 찾을 수 없습니다.");
            return "error";
        }
    }

    @PostMapping("/update")
    public String recruit_update_post(RecruitFormDto formDto){
        log.info("formDto : " + formDto);
        Long id = formDto.getRecruit().getId();
        log.info("formDto.id : " + id);
        jobOfferServiceImpl.recruit_update(id, formDto);
        return "redirect:/offer/jobopening/list";
    }

    @GetMapping("/read/{id}")
    public String recruit_read_get(@PathVariable("id") Long id, Model model){
        log.info("GET /api/offer/jobopening/read..");

        Optional<Recruit> recruitOptional = jobOfferServiceImpl.recruit_read(id);
        if(recruitOptional.isPresent()){
            Recruit recruit = recruitOptional.get();
            model.addAttribute("recruit", recruit);
        } else {
            model.addAttribute("not Found", "게시글을 찾을 수 없습니다.");
        }
        return "offer/jobopening/read";
    }



    @GetMapping("/delete/{id}")
    public String recruit_get_delete(){
        log.info("GET /api/offer/jobopening/delete/..");
        return "redirect:/api/offer/jobopening/list";
    }

    @PostMapping("/delete/{id}")
    public String recruit_post_delete(@PathVariable("id") long id){
        log.info("POST /api/offer/jobopening/delete... " + id);
        jobOfferServiceImpl.recruit_delete(id);
        return "redirect:/api/offer/jobopening/list";
    }
}
