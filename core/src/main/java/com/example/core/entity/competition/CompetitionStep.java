package com.example.core.entity.competition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "competitions_steps")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "step_number")
    private Short stepNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date_of_step")
    private LocalDateTime startDateOfStep;

    @Column(name = "end_date_of_step")
    private LocalDateTime endDateOfStep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
