package com.example.jobKoreaIt.domain.offer.service;


import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.dto.JobOfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class JobOfferServiceImpl {

    @Autowired
    private JobOfferRepository offerRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void addJobOfferDto(UserDto userDto, JobOfferDto jobOfferDto) {

        User user = new User();
        user.setUserid(userDto.getUserid());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_OFFER");
        user.setCreateAt(LocalDateTime.now());
        userRepository.save(user);
        JobOffer jobOffer = new JobOffer();
        jobOffer.setUser(user);


        jobOffer.setCompanyName(jobOfferDto.getCompanyName());
        jobOffer.setZipcode(jobOfferDto.getZipcode());
        jobOffer.setCompanyAddr1(jobOfferDto.getCompanyAddr1());
        jobOffer.setCompanyAddr2(jobOfferDto.getCompanyAddr2());
        jobOffer.setCompanyEmail(jobOfferDto.getCompanyEmail());
        jobOffer.setCompanyPhone(jobOfferDto.getCompanyPhone());
        jobOffer.setCompanyIndustry(jobOfferDto.getCompanyIndustry());
        jobOffer.setCompanyexplanation(jobOfferDto.getCompanyexplanation());

        offerRepository.save(jobOffer);
    }
}
