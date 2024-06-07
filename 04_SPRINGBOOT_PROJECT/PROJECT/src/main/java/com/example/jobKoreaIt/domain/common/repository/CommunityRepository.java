package com.example.jobKoreaIt.domain.common.repository;


import com.example.jobKoreaIt.domain.common.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {
}
