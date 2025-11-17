package com.example.core.service;

import com.example.core.dto.organisation.OrganisationInfo;
import com.example.core.dto.organisation.OrganisationResponse;
import com.example.core.entity.Organisation;
import com.example.core.repository.OrganisationRepository;
import com.example.core.util.mapper.OrganisationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с организациями {@link Organisation}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrganisationService {
    private final OrganisationRepository organisationRepository;

    /**
     * Метод для создания организации.
     *
     * @param request {@link OrganisationInfo}
     * @return {@link OrganisationResponse}
     */
    public OrganisationResponse createOrganisation(OrganisationInfo request) {
        Organisation organisation = OrganisationMapper.mapOrganisationInfoToEntity(request);
        Organisation savedOrganisation = organisationRepository.save(organisation);

        log.info("Organisation with id {} created", savedOrganisation.getId());
        return OrganisationMapper.mapEntityToOrganisationResponse(savedOrganisation);
    }

    /**
     * Метод для получения всех организаций.
     *
     * @return {@link List}{@code <}{@link OrganisationResponse}{@code >}
     */
    public List<OrganisationResponse> getAllOrganisations() {
        return organisationRepository.findAll().stream()
                .map(OrganisationMapper::mapEntityToOrganisationResponse)
                .toList();
    }
}
