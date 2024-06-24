package com.example.jobKoreaIt.domain.common.repository;

import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<JobSeeker, Long> {

}
