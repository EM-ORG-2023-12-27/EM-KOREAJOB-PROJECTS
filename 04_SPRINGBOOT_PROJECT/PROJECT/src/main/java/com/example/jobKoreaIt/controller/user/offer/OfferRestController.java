package com.example.jobKoreaIt.controller.user.offer;


import com.example.jobKoreaIt.domain.offer.dto.CompanyDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/offer")
public class OfferRestController {

    @Autowired
    private JobOfferServiceImpl jobOfferService;

    @GetMapping("/test")
    public void testFunction() {
        log.info("Testing function...");
        jobOfferService.function();
    }

//    // 회사 등록
//    @PostMapping("/company/add")
//    public void registerCompany(@RequestBody CompanyDto companyDto) {
//        log.info("Registering company...");
//        jobOfferService.CompanyRegistration(companyDto);
//    }






//    // 회사 정보 확인
//    @PostMapping("/company/read")
//    public String showCompany(Model model) {
//        log.info("Showing company information...");
//
//
//        Company company = jobOfferService.ShowCompany();
//
//
//        model.addAttribute("companyName", company.getCompanyName());
//        model.addAttribute("companyAddress", company.getCompanyaddr());
//        model.addAttribute("companyEmail", company.getCompanyEmail());
//        model.addAttribute("companyPhone", company.getCompanyPhone());
//        model.addAttribute("companyIndustry", company.getCompanyIndustry());
//
//        return "read";
//    }


}
