package com.example.jobKoreaIt.domain.common.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {
    private Long id;
    private String cat;
    private String title;
    private String username;
    private String content;
    //
}
