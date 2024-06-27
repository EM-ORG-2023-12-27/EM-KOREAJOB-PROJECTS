package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.service.RecruitServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/offer/jobopening")
@Slf4j
public class RecruitController {

    @Autowired
    private RecruitServiceImpl jobOfferServiceImpl;

    @RequestMapping("/add")
    public String recruit_add_get(){
        log.info("GET /offer/jobopening/add..");
        return "redirect:/api/offer/jobopening/add";
    }
    @GetMapping("/list")
    public void recruit_list(Model model){
        log.info("GET /api/offer/jobopening/list..");
        List<RecruitDto> recruitList = jobOfferServiceImpl.recruit_list();
        model.addAttribute("recruitList", recruitList);
    }

    @RequestMapping("/update/{id}")
    public String recruit_update_get(@PathVariable("id") long id){
        log.info("GET /offer/jobopening/update/{id}..");
        return "redirect:/api/offer/jobopening/update/" + id;
    }

    @RequestMapping("/read/{id}")
    public String recruit_read_get(@PathVariable("id") long id){
        log.info("GET /offer/jobopening/read/{id}..");
        return "redirect:/api/offer/jobopening/read/" + id;
    }

//    @RequestMapping("/list")
//    public String recruit_list_get(){
//        log.info("GET /offer/jobopening/list..");
//        return "redirect:/api/offer/jobopening/list";
//    }

    @RequestMapping("/delete/{id}")
    public String recruit_delete_get(@PathVariable("id") long id){
        log.info("GET /offer/jobopening/delete/{id}..");
        return "redirect:/api/offer/jobopening/delete/" + id;
    }
}
