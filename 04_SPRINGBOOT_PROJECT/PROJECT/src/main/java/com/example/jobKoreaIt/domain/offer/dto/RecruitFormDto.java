package com.example.jobKoreaIt.domain.offer.dto;


import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitFormDto {

    private Recruit recruit;
    private List<Company> companies;


    public String getCompany() {
        return null;
    }
}
