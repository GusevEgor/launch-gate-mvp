package com.example.core.entity.competition;

import com.example.core.dto.contact.ContactType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "competitions_manager_contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionManagerContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_of_contact")
    @Enumerated(EnumType.STRING)
    private ContactType typeOfContact;

    @Column(name = "contact")
    private String contact;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private CompetitionManager manager;
}

