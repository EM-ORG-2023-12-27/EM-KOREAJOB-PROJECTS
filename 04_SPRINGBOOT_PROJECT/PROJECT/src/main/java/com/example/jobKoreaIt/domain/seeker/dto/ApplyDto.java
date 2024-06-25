package com.example.jobKoreaIt.domain.seeker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyDto {
    private String Name;
    private String Tell;
    private String Email;
    private String Addr; //주소
    private String Nationality; //국적
    private String BirthDate; //생년월일
    private String ExpectedSalary; //희망연봉
    private String MilitaryService; //병역사항
    private String School; //최종학력
    private String Carrer; //경력사항 (신입 or 경력)

    private String Objective; //지원종목
}
