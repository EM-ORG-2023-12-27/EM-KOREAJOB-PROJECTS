package com.example.jobKoreaIt.domain.seeker.repository;


import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker,Long> {
    Optional<JobSeeker> findFirstByNicknameAndTel(String nickname, String tel);
    Optional<JobSeeker> findByUsernameAndTelAndNickname(String username,String tel,String nickname);
}
