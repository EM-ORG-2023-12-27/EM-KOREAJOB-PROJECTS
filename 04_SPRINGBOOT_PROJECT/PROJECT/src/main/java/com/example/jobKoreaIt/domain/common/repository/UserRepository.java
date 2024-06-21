package com.example.jobKoreaIt.domain.common.repository;


import com.example.jobKoreaIt.domain.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    User findByUsername(String username);

<<<<<<< HEAD
=======

    User findByEmail(String email);
    User findByUsername(String username);

>>>>>>> c96d487755f3033defd6534c4af81f9f88418dba
    //사용자명을 기준으로 첫번째 결과만 반환
    Optional<User> findFirstByUsername(String username);
}

