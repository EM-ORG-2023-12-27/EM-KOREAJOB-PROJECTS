package com.example.jobKoreaIt.domain.common.entity;

import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.entity.Resume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "recruit_id", foreignKey = @ForeignKey(name="FK_APPLY_Recruit"))
    private Recruit recruit;

}
