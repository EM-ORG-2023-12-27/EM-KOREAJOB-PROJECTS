package com.example.jobKoreaIt.domain.common.repository;

import com.example.jobKoreaIt.domain.common.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

}