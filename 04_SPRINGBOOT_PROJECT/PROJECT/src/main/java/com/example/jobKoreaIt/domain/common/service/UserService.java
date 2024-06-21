package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;

public interface UserService {
    User getUser(SeekerDto userDto,String type);

    User getUser(OfferDto userDto,String type);

    boolean confirmIdPw(String username, String password);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    void saveUser(User user);

    JobSeeker getSeeker(SeekerDto seekerDto);

    JobOffer getOffer(OfferDto offerDto);


}
