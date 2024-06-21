package com.example.jobKoreaIt.domain.offer.entity;


<<<<<<< HEAD
=======
import com.example.jobKoreaIt.domain.common.entity.User;
>>>>>>> c96d487755f3033defd6534c4af81f9f88418dba
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
<<<<<<< HEAD


    private String offernumber;
    private String zipcode;
    private String offername;
    private String offeraddress;
=======
    private String offername;
    private String offernumber;
    private String zipcode;
    private String offeraddress;

    private String offerName;
>>>>>>> c96d487755f3033defd6534c4af81f9f88418dba
    private String offerTuserel;




}
