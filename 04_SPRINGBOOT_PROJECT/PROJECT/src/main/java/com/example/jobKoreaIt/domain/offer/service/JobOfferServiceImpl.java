package com.example.jobKoreaIt.domain.offer.service;


import com.example.jobKoreaIt.domain.offer.dto.CompanyDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.repository.CompanyRepository;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class JobOfferServiceImpl {

    @Autowired
    private JobOfferRepository offerRepository;

    @Transactional(rollbackFor = Exception.class)
    public void function(){
        log.info("TEST...");
    }

    //-----------------------
    //박정현 Company CRUD
    //-----------------------
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional(rollbackFor = Exception.class)
    public void CompanyRegistration(CompanyDto companyDto){
        log.info("회사등록...");
        Company company = new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyaddr(companyDto.getCompanyaddr());
        company.setCompanyEmail(companyDto.getCompanyEmail());
        company.setCompanyPhone(companyDto.getCompanyPhone());
        company.setCompanyIndustry(companyDto.getCompanyIndustry());
        company.setCompanyexplanation(companyDto.getCompanyexplanation());
        companyRepository.save(company);
    }
    @Transactional(rollbackFor = Exception.class)
    public void CompanyUpdate(Company company){
        log.info("회사정보수정...");
        Company existingCompany = companyRepository.findById(company.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + company.getId()));

        existingCompany.setCompanyName(company.getCompanyName());
        existingCompany.setCompanyaddr(company.getCompanyaddr());
        existingCompany.setCompanyEmail(company.getCompanyEmail());
        existingCompany.setCompanyPhone(company.getCompanyPhone());
        existingCompany.setCompanyIndustry(company.getCompanyIndustry());
        existingCompany.setCompanyexplanation(company.getCompanyexplanation());

        companyRepository.save(existingCompany);

    }
    @Transactional(rollbackFor = Exception.class)
    public void RemoveCompany(Long id){
        log.info("회사정보 삭제...");
        companyRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Company showCompany() {
        log.info("회사정보 조회...");
        return companyRepository.findById(9L).orElseThrow(() -> new RuntimeException("Company not found"));
    }



}
