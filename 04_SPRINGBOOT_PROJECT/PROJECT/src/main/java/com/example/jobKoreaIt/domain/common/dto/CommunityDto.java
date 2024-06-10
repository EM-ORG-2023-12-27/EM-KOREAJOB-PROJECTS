package com.example.jobKoreaIt.domain.common.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@ToString
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
