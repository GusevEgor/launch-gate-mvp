package com.example.core.service;

import com.example.core.dto.organisation.OrganisationInfo;
import com.example.core.dto.organisation.OrganisationResponse;
import com.example.core.entity.Organisation;
import com.example.core.repository.OrganisationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;

    public OrganisationResponse createOrganisation(OrganisationInfo request) {
        Organisation organisation = new Organisation();
        organisation.setName(request.getName());
        organisationRepository.save(organisation);

        return new OrganisationResponse(organisation.getId(), organisation.getName());
    }

    public List<OrganisationResponse> getAllOrganisations() {
       return organisationRepository.findAll().stream()
               .map(organisation -> new OrganisationResponse(organisation.getId(), organisation.getName()))
               .toList();
    }
}
