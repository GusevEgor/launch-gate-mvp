package com.example.core.entity.competition;

import com.example.core.entity.Media;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "competition_media")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_block_id")
    private CompetitionBlock competitionBlock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private Media media;

    @Column(name = "position")
    private Long position;
}
