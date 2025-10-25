package com.example.core.controller;

import com.example.core.dto.organisation.OrganisationInfo;
import com.example.core.dto.organisation.OrganisationResponse;
import com.example.core.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organisations")
@RequiredArgsConstructor
public class OrganisationController {
    private final OrganisationService organisationService;

    @PostMapping("/create")
    public void createOrganisation(OrganisationInfo request) {
        organisationService.createOrganisation(request);
    }

    @GetMapping("/get-all")
    public List<OrganisationResponse> getAllOrganisations() {
        return organisationService.getAllOrganisations();
    }
}
