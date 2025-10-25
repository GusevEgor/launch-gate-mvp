package com.example.core.entity.competition;


import com.example.core.dto.competition.CompetitionFormat;
import com.example.core.dto.competition.CompetitionType;
import com.example.core.entity.Organisation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.List;


@Entity
@Table(name = "competitions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_draft")
    private Boolean isDraft;

    @Column(name = "name")
    private String name;

    @Column(name = "competition_type")
    @Enumerated(EnumType.STRING)
    private CompetitionType competitionType;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "registration_start_date")
    private Long registrationStartDate;

    @Column(name = "registration_end_date")
    private Long registrationEndDate;

    @Column(name = "competition_start_date")
    private Long competitionStartDate;

    @Column(name = "competition_end_date")
    private Long competitionEndDate;

    @Column(name = "result_start_date")
    private Long resultStartDate;

    @Column(name = "result_end_date")
    private Long resultEndDate;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "format_of_competition")
    @Enumerated(EnumType.STRING)
    private CompetitionFormat formatOfCompetition;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "min_participant_age")
    private Integer minParticipantAge;

    @Column(name = "max_participant_age")
    private Integer maxParticipantAge;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "is_team_required")
    private Boolean isTeamRequired;

    @Column(name = "min_team_size")
    private Integer minTeamSize;

    @Column(name = "max_team_size")
    private Integer maxTeamSize;

    @Column(name = "is_country")
    private Boolean isCountry;


    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<CompetitionTag> competitionTags;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<CompetitionContact> competitionContacts;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<CompetitionPrize> competitionPrizes;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<CompetitionManager> competitionManagers;
}
