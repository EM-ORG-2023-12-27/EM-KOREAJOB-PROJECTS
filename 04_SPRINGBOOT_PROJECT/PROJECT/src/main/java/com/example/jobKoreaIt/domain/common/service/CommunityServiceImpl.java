package com.example.jobKoreaIt.domain.common.service;


import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import com.example.jobKoreaIt.domain.common.dto.Criteria;
import com.example.jobKoreaIt.domain.common.dto.PageDto;
import com.example.jobKoreaIt.domain.common.entity.Community;
import com.example.jobKoreaIt.domain.common.repository.CommunityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CommunityServiceImpl {

    @Autowired
    private CommunityRepository communityRepository;

    private String uploadDir = "c:\\upload";


    @Transactional(rollbackFor = Exception.class)
    public boolean addCommunity(CommunityDto dto) throws IOException {

        Community community = new Community();
        community.setTitle(dto.getTitle());
        community.setContent(dto.getContent());
        community.setUsername(dto.getUsername());
        community.setCount(0L);
        community.setRegdate(LocalDateTime.now());
        community.setCategory(dto.getCategory());

        MultipartFile[] files = dto.getFiles();
        List<String> filenames = new ArrayList<String>();
        List<String> filesizes = new ArrayList<String>();
        if(dto.getFiles().length >= 1 && dto.getFiles()[0].getSize()!=0L)
        {
            //Upload Dir 미존재시 생성
            String path = uploadDir+ File.separator+dto.getUsername()+File.separator+ UUID.randomUUID();
            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            //COMMUNITY 경로 추가
            community.setDirpath(dir.toString());

            for(MultipartFile file  : dto.getFiles())
            {
                System.out.println("--------------------");
                System.out.println("FILE NAME : " + file.getOriginalFilename());
                System.out.println("FILE SIZE : " + file.getSize() + " Byte");
                System.out.println("--------------------");

                //파일명 추출
                String filename = file.getOriginalFilename();
                //파일객체 생성
                File fileobj = new File(path,filename);
                //업로드
                file.transferTo(fileobj);

                //filenames 저장
                filenames.add(filename);
                filesizes.add(file.getSize()+"");
            }
            community.setFilename(filenames.toString());
            community.setFilesize(filesizes.toString());
        }


        community  =    communityRepository.save(community);

        boolean issaved =  communityRepository.existsById(community.getNo());

        return issaved;
    }



    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> GetCommunityList(Criteria criteria) {
        Map<String,Object> returns = new HashMap<String,Object>();

        int totalcount = (int) communityRepository.count();
        System.out.println("COUNT  :" + totalcount);
        //PageDto 만들기
        PageDto pagedto = new PageDto(totalcount,criteria);

        //시작 게시물 번호 구하기(수정) - OFFSET
        int offset =(criteria.getPageno()-1) * criteria.getAmount();    //1page = 0, 2page = 10


        List<Community> list = null;

        list  =  communityRepository.findCommunityAmountStart(pagedto.getCriteria().getAmount(),offset);

        returns.put("list",list);
        returns.put("pageDto",pagedto);
        returns.put("total",totalcount);


        return returns;
    }
    @Transactional(rollbackFor = Exception.class)
    public Community getCommunity(Long no) {
        return communityRepository.findById(no).get();

    }
}
