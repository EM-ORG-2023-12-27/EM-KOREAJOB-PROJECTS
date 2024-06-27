package com.example.jobKoreaIt.domain.offer.repository;


import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    void deleteAllByRecruit(Recruit recruit);

    List<Company> findAllByRecruit (Recruit recruit);
}
