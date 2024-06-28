package com.example.jobKoreaIt.domain.offer.repository;

import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    Recruit findByTitle(String applyDto);

}
