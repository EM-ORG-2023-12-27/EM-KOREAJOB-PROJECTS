package com.example.jobKoreaIt.domain.apply.repository;

import com.example.jobKoreaIt.domain.apply.entity.ApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<ApplyEntity,Long> {
}
