package com.example.core.entity.user;

import com.example.core.entity.competition.CompetitionTeam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "team_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_in_team")
    private String roleInTeam;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private CompetitionTeam competitionTeam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
