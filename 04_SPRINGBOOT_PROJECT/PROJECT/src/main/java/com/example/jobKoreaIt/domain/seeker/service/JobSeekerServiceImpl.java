package com.example.jobKoreaIt.domain.seeker.service;



import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.Seeker;
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
            // User 엔티티 생성 및 저장
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            // 기타 필요한 필드 설정

            userRepository.save(user);

            // Seeker 엔티티 생성 및 저장
            Seeker seeker = new Seeker();
            seeker.setUsername(seekerDto.getUsename());
            seeker.setPassword(seekerDto.getPassword());
            seeker.setTel(seekerDto.getTel());
            seeker.setNickname(seekerDto.getNickname());
            seeker.setZipcode(seekerDto.getZipcode());
            seeker.setAddr1(seekerDto.getAddr1());
            seeker.setAddr2(seekerDto.getAddr2());
            // 기타 필요한 필드 설정

            jobSeekerRepository.save(seeker);

            return true;
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생: ", e);
            return false;
        }
    }
}




