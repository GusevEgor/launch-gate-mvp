package com.example.core.util.competition;

import com.example.core.dto.contact.ContactInfo;
import com.example.core.dto.contact.manager.CompetitionManagerContactsInfo;
import com.example.core.dto.manager.CompetitionManagerInfo;
import com.example.core.entity.competition.CompetitionManager;
import com.example.core.entity.competition.CompetitionManagerContact;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

/**
 * Маппер для работы с менеджерами мероприятия {@link CompetitionManager}.
 */
@UtilityClass
public class CompetitionManagerMapper {

    /**
     * Метод для конвертации {@link CompetitionManagerInfo} в {@link CompetitionManager}.
     *
     * @param managerInfo {@link CompetitionManagerInfo}
     * @return {@link CompetitionManager}
     */
    public CompetitionManager mapCompetitionManagerInfoToEntity(CompetitionManagerInfo managerInfo) {
        CompetitionManager manager = new CompetitionManager();
        manager.setRoleLabel(managerInfo.getRole());
        manager.setIsCreator(managerInfo.getIsCreator());
        manager.setInSystem(managerInfo.getInSystem());
        manager.setContacts(managerInfo.getContacts()
                .stream()
                .map(contactInfo -> {
                    CompetitionManagerContact contact = mapCompetitionManagerContactInfoToEntity(contactInfo);
                    contact.setManager(manager);
                    return contact;
                })
                .collect(Collectors.toList())
        );

        return manager;
    }

    /**
     * Метод для конвертации {@link CompetitionManager} в {@link CompetitionManagerInfo}.
     *
     * @param manager {@link CompetitionManager}
     * @return {@link CompetitionManagerInfo}
     */
    public CompetitionManagerInfo mapEntityToCompetitionManagerInfo(CompetitionManager manager) {
        CompetitionManagerInfo managerInfo = new CompetitionManagerInfo();
        if (manager.getUser() != null) {
            managerInfo.setUserId(manager.getUser().getId());
        } else {
            managerInfo.setUserId(null);
        }
        managerInfo.setRole(manager.getRoleLabel());
        managerInfo.setIsCreator(manager.getIsCreator());
        managerInfo.setInSystem(manager.getInSystem());
        managerInfo.setContacts(manager.getContacts()
                .stream()
                .map(CompetitionManagerMapper::mapEntityToCompetitionManagerContactsInfo)
                .collect(Collectors.toList()));

        return managerInfo;
    }

    /**
     * Метод для конвертации {@link CompetitionManagerContactsInfo} в {@link CompetitionManagerContact}.
     * Преобразовывает контакты менеджера
     *
     * @param contactInfo {@link CompetitionManagerContactsInfo}
     * @return {@link CompetitionManagerContact}
     */
    public CompetitionManagerContact mapCompetitionManagerContactInfoToEntity(
            CompetitionManagerContactsInfo contactInfo) {
        CompetitionManagerContact contact = new CompetitionManagerContact();
        contact.setTypeOfContact(contactInfo.getContactInfo().getContactsType());
        contact.setContact(contactInfo.getContactInfo().getSource());
        contact.setIsPrimary(contactInfo.getIsPrimary());
        return contact;
    }

    /**
     * Метод для конвертации {@link CompetitionManagerContact} в {@link CompetitionManagerContactsInfo}.
     *
     * @param competitionManagerContact {@link CompetitionManagerContact}
     * @return {@link CompetitionManagerContactsInfo}
     */
    public CompetitionManagerContactsInfo mapEntityToCompetitionManagerContactsInfo(
            CompetitionManagerContact competitionManagerContact) {
        ContactInfo contactInfo = new ContactInfo();
        CompetitionManagerContactsInfo managerContactsInfo = new CompetitionManagerContactsInfo();

        contactInfo.setContactsType(competitionManagerContact.getTypeOfContact());
        contactInfo.setSource(competitionManagerContact.getContact());

        managerContactsInfo.setContactInfo(contactInfo);
        managerContactsInfo.setIsPrimary(competitionManagerContact.getIsPrimary());
        return managerContactsInfo;
    }
}