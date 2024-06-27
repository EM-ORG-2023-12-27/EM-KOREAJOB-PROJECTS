package com.example.jobKoreaIt.domain.offer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    // 이름
    private String companyNumber; //// 사업자 번호
    private String zipcode;
    private String companyAddr1;     // 주소
    private String companyAddr2;     // 주소

    private String companyEmail;    // 이메일
    private String companyPhone;    // 폰
    private String companyIndustry; // 업종
    private String companyexplanation;  // 회사 설명

    @ManyToOne
    @JoinColumn(name = "recruit_id",foreignKey = @ForeignKey(name="FK_Recruit_Company",
            foreignKeyDefinition = "FOREIGN KEY(recruit_id) REFERENCES recruit(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Recruit recruit;
}
