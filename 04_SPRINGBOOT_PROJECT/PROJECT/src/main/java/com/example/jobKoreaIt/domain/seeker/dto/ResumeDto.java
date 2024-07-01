package com.example.jobKoreaIt.domain.seeker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private LocalDate creationDate;

    //--------------
    //새로 추가됨
    //--------------
    private String schoolName;
    private String major;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime graduationYear;

    private String summary;




}
