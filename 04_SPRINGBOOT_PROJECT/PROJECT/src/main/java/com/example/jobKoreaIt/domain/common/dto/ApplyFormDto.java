package com.example.jobKoreaIt.domain.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplyFormDto {

    @NotBlank(message = "제목을 입력해주세요")
    private String jobTitle;

    @NotBlank(message = "회사 이름을 입력해주세요")
    private String companyName;
}