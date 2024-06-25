package com.example.jobKoreaIt.domain.offer.entity;


import com.example.jobKoreaIt.domain.common.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruit_id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

}
