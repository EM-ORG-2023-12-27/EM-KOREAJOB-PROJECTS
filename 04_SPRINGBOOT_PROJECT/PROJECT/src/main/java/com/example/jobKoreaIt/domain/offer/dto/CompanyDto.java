package com.example.jobKoreaIt.domain.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String companyName;     // 이름
    private String companyaddr;     // 주소
    private String companyEmail;    // 이메일
    private String companyPhone;    // 연락처
    private String companyIndustry; // 업종
    private String companyexplanation;

}
