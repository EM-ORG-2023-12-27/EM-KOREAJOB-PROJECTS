package com.example.jobKoreaIt.domain.seeker.entity;

import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Apply {

    @Id
    private Long apply_id;

    //User 1-N Lend
    @ManyToOne
    @JoinColumn(name = "recruit_id",foreignKey = @ForeignKey(name="FK_APPLY_RECRUIT",
            foreignKeyDefinition = "FOREIGN KEY(recruit_id) REFERENCES Recruit(recruit_id) ON DELETE CASCADE ON UPDATE CASCADE" ))
    private Recruit recruit;



    // title //타이틀
    // sector<companyIndustry>; // 지원부문
    // resume //이력서


}
