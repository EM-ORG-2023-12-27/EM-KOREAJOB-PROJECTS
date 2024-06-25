package com.example.jobKoreaIt.domain.apply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplyEntity {

    @Id
    private Long Id; //채용공고랑 1대 n관계의 외래키

    private String AnnouncementName; //공고명 ex-백엔드 개발자
    private String name;
    private String tell;
    private String email;
    private String school;

    private String Objective;//지원분야
}
