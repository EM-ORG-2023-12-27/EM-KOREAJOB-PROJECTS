package com.example.jobKoreaIt.domain.seeker.entity;

import com.example.jobKoreaIt.domain.common.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resume {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "seeker_id",foreignKey = @ForeignKey(name="FK_SEEKER_RESUME",
            foreignKeyDefinition ="FOREIGN KEY(seeker_id) REFERENCES job_seeker(id) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private JobSeeker jobSeeker;

    // 학력
    private String schoolName;
    private String major;
    private String graduationYear;

    @ElementCollection
    public List<Career> careers;

    // 기술 및 자격증
    @ElementCollection
    private List<String> certificationName;

    // 자기소개서
    @Column(length = 1000)
    private String summary;

    // 기타
    private String hobbies;

    // 작성 날짜
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime creationDate;


}
