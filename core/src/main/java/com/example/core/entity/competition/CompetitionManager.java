package com.example.core.entity.competition;

import com.example.core.dto.manager.CompetitionManagerRole;
import com.example.core.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "competitions_manager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_label")
    @Enumerated(EnumType.STRING)
    private CompetitionManagerRole roleLabel;

    @Column(name = "is_creator")
    private Boolean isCreator;

    @Column(name = "in_system")
    private Boolean inSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CompetitionManagerContact> contacts;
}


