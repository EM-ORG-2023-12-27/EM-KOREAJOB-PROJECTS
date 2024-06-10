package com.example.jobKoreaIt.domain.common.service;

// DTO >> Entity (Entity Class)
// Entity >> DTO (DTO Class)

import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import com.example.jobKoreaIt.domain.common.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{

    @Autowired
    private CommunityRepository communityRepository;

    @Transactional(rollbackFor = Exception.class)
    public boolean addCommunity(CommunityDto dto){
        communityRepository.save();
        return false;
    }



}
