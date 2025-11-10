package com.example.core.controller;

import com.example.core.aspect.LogBefore;
import com.example.core.dto.organisation.OrganisationInfo;
import com.example.core.dto.organisation.OrganisationResponse;
import com.example.core.service.OrganisationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с организациями.
 */
@RestController
@RequestMapping("/api/v1/organisations")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с организациями",
        description = "API для работы с организациями: создание, получение")
public class OrganisationController {
    private final OrganisationService organisationService;

    /**
     * Эндпоинт для создания организации.
     *
     * @param request {@link OrganisationInfo}
     * @return {@link OrganisationResponse}
     */
    @LogBefore
    @PostMapping("/create")
    @Operation(summary = "Создание организации")
    public OrganisationResponse createOrganisation(OrganisationInfo request) {
        return organisationService.createOrganisation(request);
    }

    /**
     * Эндпоинт для получения списка организаций.
     *
     * @return {@link List}{@code <}{@link OrganisationResponse}{@code >}
     */
    @LogBefore
    @Operation(summary = "Получение списка организаций")
    @GetMapping("/get-all")
    public List<OrganisationResponse> getAllOrganisations() {
        return organisationService.getAllOrganisations();
    }
}
