package com.example.jobKoreaIt.domain.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDto {

    private String AnnouncementName; //공고명 ex-백엔드 개발자
    private String name;
    private String tell;
    private String email;
    private String school; //최종학력

    private String Objective; //지원분야
}
