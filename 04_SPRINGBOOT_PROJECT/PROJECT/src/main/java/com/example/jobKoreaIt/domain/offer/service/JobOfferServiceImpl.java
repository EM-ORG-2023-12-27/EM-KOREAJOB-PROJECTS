package com.example.jobKoreaIt.domain.offer.service;


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
    public void CompanyRegistration(){
        log.info("회사등록...");
    }
    @Transactional(rollbackFor = Exception.class)
    public void ModifyCompany(){
        log.info("회사정보수정...");
    }
    @Transactional(rollbackFor = Exception.class)
    public void RemoveCompany(){
        log.info("회사정보 삭제...");
    }
    @Transactional(rollbackFor = Exception.class)
    public void AddCompany(){
        log.info("회사정보 추가...");
    }
    @Transactional(rollbackFor = Exception.class)
    public void ShowCompany(){
        log.info("회사정보 확인...");
    }
}
