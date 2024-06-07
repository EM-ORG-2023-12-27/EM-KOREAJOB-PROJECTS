package com.example.jobKoreaIt.domain.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDto {
    private Long id;
    private String cat;
    private String title;
    private String username;
    private String content;
//    private MultipartFile file;
}
