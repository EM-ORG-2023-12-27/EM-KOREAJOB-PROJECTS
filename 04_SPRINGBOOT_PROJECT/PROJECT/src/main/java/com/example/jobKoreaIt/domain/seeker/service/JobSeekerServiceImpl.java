package com.example.jobKoreaIt.domain.seeker.service;



import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class JobSeekerServiceImpl {


    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void function(){
        log.info("TEST...");
    }


    //------------------
    //이력서 CRUD (이동환)
    //------------------
    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional(rollbackFor = Exception.class)
    public void resume_add(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_list(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_read(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_delete(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void resume_update(){
        log.info("TEST...");
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean memberRegistration(UserDto userDto, SeekerDto seekerDto) {
        try {


            // Seeker 엔티티 생성 및 저장
            JobSeeker seeker = new JobSeeker();
            seeker.setUsername(seekerDto.getUsername());
            seeker.setPassword(seekerDto.getPassword());

            seeker.setUsername(seekerDto.getUsername());
            seeker.setPassword(seekerDto.getPassword());
            seeker.setTel(seekerDto.getTel());
            seeker.setNickname(seekerDto.getNickname());
            seeker.setZipcode(seekerDto.getZipcode());
            seeker.setAddr1(seekerDto.getAddr1());
            seeker.setAddr2(seekerDto.getAddr2());

            jobSeekerRepository.save(seeker);

            return true;
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생: ", e);
            return false;
        }
    }
}




