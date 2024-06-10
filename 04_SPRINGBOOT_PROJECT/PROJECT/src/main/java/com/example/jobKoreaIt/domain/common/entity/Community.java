package com.example.jobKoreaIt.domain.common.entity;


import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Community_table")
public class Community {
    @Id // pk 컬럼 지정. 반드시 필수.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mysql 기준으로 Auto_increment
    private Long id;

    @Column(length = 8, nullable = false) // length 컬럼 크기, not null
    private String cat;

    @Column // default 값 255, null 가능.
    private String title;

    @Column
    private String username;

    @Column(length = 500)
    private String content;

//    public static Community toSaveEntity(CommunityDto communityDto){
//
//    }
    // 빌더 패턴을 사용햇자나! 그럼 빌더로 써야지.


}
