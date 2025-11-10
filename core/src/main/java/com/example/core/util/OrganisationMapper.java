package com.example.core.util;

import com.example.core.dto.organisation.OrganisationInfo;
import com.example.core.dto.organisation.OrganisationResponse;
import com.example.core.entity.Organisation;
import lombok.experimental.UtilityClass;

/**
 * Маппер для работы с организациями {@link Organisation}.
 */
@UtilityClass
public class OrganisationMapper {

    /**
     * Метод для конвертации {@link OrganisationInfo} в {@link Organisation}.
     *
     * @param request {@link OrganisationInfo}
     * @return {@link Organisation}
     */
    public Organisation mapOrganisationInfoToEntity(OrganisationInfo request) {
        Organisation organisation = new Organisation();
        organisation.setName(request.getName());
        return organisation;
    }

    /**
     * Метод для конвертации {@link Organisation} в {@link OrganisationResponse}.
     *
     * @param organisation {@link Organisation}
     * @return {@link OrganisationResponse}
     */
    public OrganisationResponse mapEntityToOrganisationResponse(Organisation organisation) {
        OrganisationResponse response = new OrganisationResponse();
        response.setId(organisation.getId());
        response.setName(organisation.getName());
        return response;
    }
}
