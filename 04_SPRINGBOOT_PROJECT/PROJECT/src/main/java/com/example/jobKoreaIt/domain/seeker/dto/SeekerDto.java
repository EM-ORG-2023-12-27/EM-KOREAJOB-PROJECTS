package com.example.jobKoreaIt.domain.seeker.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SeekerDto {
    private Long id;
    private String tel;
    private String zipcode;
    private String addr1;
    private String addr2;
}
