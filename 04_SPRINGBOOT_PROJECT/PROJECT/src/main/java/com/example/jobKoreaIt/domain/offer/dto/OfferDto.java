package com.example.jobKoreaIt.domain.offer.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private String id;
    private String username;
    private String password;
    private String repassword;
    private String offername;
    private String offernumber;
    private String zipcode;
    private String offeraddress;
    private String offertel;


}
