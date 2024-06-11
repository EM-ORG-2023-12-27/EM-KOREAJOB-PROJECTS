package com.example.jobKoreaIt.domain.common.repository;


import com.example.jobKoreaIt.domain.common.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {


    @Query(value = "SELECT * FROM KoreaJob.community ORDER BY no DESC LIMIT :amount OFFSET :offset", nativeQuery = true)
    List<Community> findCommunityAmountStart(@Param("amount") int amount, @Param("offset") int offset);

}
