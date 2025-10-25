package com.example.core.entity.competition;

import com.example.core.dto.contact.ContactType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "competition_contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_of_contact")
    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    @Column(name = "description")
    private String description;

    @Column(name = "contact")
    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
