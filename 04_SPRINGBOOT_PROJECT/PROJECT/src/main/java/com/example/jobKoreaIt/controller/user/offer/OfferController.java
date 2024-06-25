package com.example.jobKoreaIt.controller.user.offer;

import com.example.jobKoreaIt.domain.offer.dto.CompanyDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private JobOfferServiceImpl jobOfferService;


    @GetMapping("/join")
    public void join_get(){
        log.info("GET /offer/join...");
    }


    @GetMapping("/company/add")
    public void companyAdd(){
    }

    @PostMapping("/company/add")
    public String registerCompany(@ModelAttribute CompanyDto companyDto) {
        log.info("Registering company...");
        jobOfferService.CompanyRegistration(companyDto); // 서비스 메서드 호출
        return "redirect:/offer/company/read";
    }
    @GetMapping("/company/read")
    public String showCompany(Model model) {
        log.info("Showing company information...");

        Company company = jobOfferService.showCompany();

        model.addAttribute("companyName", company.getCompanyName());
        model.addAttribute("companyNumber",company.getCompanyNumber());
        model.addAttribute("zipcode",company.getZipcode());
        model.addAttribute("companyaddr1", company.getCompanyAddr1());
        model.addAttribute("companyaddr2", company.getCompanyAddr2());
        model.addAttribute("companyEmail", company.getCompanyEmail());
        model.addAttribute("companyPhone", company.getCompanyPhone());
        model.addAttribute("companyIndustry", company.getCompanyIndustry());
        model.addAttribute("companyexplanation",company.getCompanyexplanation());

        return "offer/company/read";
    }
    @GetMapping("/company/list")
    public void companyList(){}
    @GetMapping("/company/delete")
    public void compayDelete(){}
    @GetMapping("/company/update")
    public void companyUpdate(@ModelAttribute  Company company){
        log.info("Registering company...");
    }

    @PostMapping("/company/delete")
    public String deleteCompany(@RequestParam("id") Long id) {
        jobOfferService.RemoveCompany(id);
        return "redirect:/offer/company/read";
    }

    // 회사 정보 추가
    @PostMapping("/company/update")
    public void addCompany( @RequestParam("id") Long id,
                            @RequestParam("companyName") String companyName,
                                @RequestParam("companyNumber") String companyNumber,
                                @RequestParam("zipcode") String zipcode,
                                @RequestParam("companyaddr1") String companyaddr1,
                            @RequestParam("companyaddr2") String companyaddr2,
                            @RequestParam("companyEmail") String companyEmail,
                            @RequestParam("companyPhone") String companyPhone,
                            @RequestParam("companyIndustry") String companyIndustry,
                            @RequestParam("companyexplanation") String companyExplanation) {
        log.info("Adding company information...");

        Company company = new Company();
        company.setId(id);
        company.setCompanyName(companyName);
        company.setCompanyNumber(companyNumber);
        company.setZipcode(zipcode);
        company.setCompanyAddr1(companyaddr1);
        company.setCompanyAddr2(companyaddr2);
        company.setCompanyEmail(companyEmail);
        company.setCompanyPhone(companyPhone);
        company.setCompanyIndustry(companyIndustry);
        company.setCompanyexplanation(companyExplanation);

        jobOfferService.CompanyUpdate(company);

    }

}


