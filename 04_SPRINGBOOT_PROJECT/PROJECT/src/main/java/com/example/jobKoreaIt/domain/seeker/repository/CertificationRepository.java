package com.example.jobKoreaIt.domain.seeker.repository;


import com.example.jobKoreaIt.domain.seeker.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification,Long> {
}
