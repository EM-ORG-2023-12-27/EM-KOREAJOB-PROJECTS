package com.example.jobKoreaIt.domain.offer.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="offer")
public class JobOffer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String offertel;
    private String nickname;


    private String offernumber;
    private String zipcode;
    private String offername;
    private String offeraddress;
    private String offerTuserel;



}
