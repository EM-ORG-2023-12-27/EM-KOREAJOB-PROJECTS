package com.example.jobKoreaIt.domain.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyDto {
    private Long id;
    @NotBlank(message = "제목을 지어주세요")
    private String jobTitle;
    private String companyName;
    private String status;
}
