package com.example.jobKoreaIt.domain.common.entity;

import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", foreignKey = @ForeignKey(name="FK_APPLY_JOB_SEEKER"))
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "resume_id", foreignKey = @ForeignKey(name="FK_APPLY_RESUME"))
    private Resume resume;

    private String jobTitle;
    private String companyName;
    private String status;

}