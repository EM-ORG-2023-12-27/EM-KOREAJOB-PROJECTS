package com.example.jobKoreaIt.domain.offer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private Long id;
    private String offerName;
    private String offerAddress;
}
