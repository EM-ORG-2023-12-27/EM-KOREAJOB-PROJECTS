package com.example.jobKoreaIt.domain.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitDto {
    private Long id;
    private String title;       // 제목
    private String career;      // 경력
    private String ability;     // 학력
    private String jobwork;     // 근무형태
    private String money;       // 급여
    private String jobspecial;  // 우대사항
    private String jobzone;     // 근무지역
    private String welfare;     // 복리후생(복지)
    private String jobplace;    // 근무지위치
    private LocalDateTime startTime; // 접수 시작 시간
    private LocalDateTime endTime;   // 접수 종료 시간
    private String jobway;      // 접수방법
    private String jobpapers;   // 제출서류
    private MultipartFile files;       // 이미지
//    private MultipartFile[] files;  // 이미지
}