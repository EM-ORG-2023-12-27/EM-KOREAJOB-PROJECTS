package com.example.jobKoreaIt.domain.offer.service;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
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

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void function(){
        log.info("TEST...");
    }

    //-----------------------
    //박정현 Company CRUD
    //-----------------------


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





    @Transactional(rollbackFor = Exception.class)
    public boolean memberRegistration(UserDto userDto, OfferDto offerDto) {
        try {
            // Offer 엔티티 생성 및 저장
            JobOffer offer = new JobOffer();
            offer.setUsername(offerDto.getUsername());
            offer.setPassword(offerDto.getPassword());

            offer.setUsername(offerDto.getUsername());
            offer.setPassword(offerDto.getPassword());
            offer.setOffertel(offerDto.getOffertel());
            offer.setNickname(offerDto.getNickname());
            offer.setOffername(offerDto.getOffername());
            offer.setOffernumber(offerDto.getOffernumber());
            offer.setZipcode(offerDto.getZipcode());
            offer.setOfferaddress(offerDto.getOfferaddress());

            offerRepository.save(offer);

            return true;
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생: ", e);
            return false;
        }
    }
}


