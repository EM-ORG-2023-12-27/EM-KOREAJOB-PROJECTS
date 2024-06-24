package com.example.jobKoreaIt.domain.common.entity;


<<<<<<< HEAD
import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import jakarta.persistence.*;
import lombok.*;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.tokens.ScalarToken;

import java.time.LocalDateTime;
>>>>>>> dev

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
<<<<<<< HEAD
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

=======
@Data
public class Community {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long no;
    private String username;
    private String title;
    private String content;
    private String category;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;
    private Long count;
>>>>>>> dev

}
