package com.example.core.entity.competition;


import com.example.core.dto.prize.PrizeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "competition_prizes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "medal_place")
    private Integer medalPlace;

    @Column(name = "type_of_prize")
    @Enumerated(EnumType.STRING)
    private PrizeType typeOfPrize;

    @Column(name = "value_of_prize")
    private String valueOfPrize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
