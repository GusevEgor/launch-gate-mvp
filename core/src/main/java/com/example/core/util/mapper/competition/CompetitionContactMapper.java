package com.example.core.util.mapper.competition;

import com.example.core.dto.contact.ContactInfo;
import com.example.core.dto.contact.event.EventContact;
import com.example.core.entity.competition.CompetitionContact;
import lombok.experimental.UtilityClass;

/**
 * Маппер для работы с контактами мероприятия {@link CompetitionContact}.
 */
@UtilityClass
public class CompetitionContactMapper {

    /**
     * Метод для конвертации {@link EventContact} в {@link CompetitionContact}.
     *
     * @param contactInfo {@link EventContact}
     * @return {@link CompetitionContact}
     */
    public CompetitionContact mapEventContactToEntity(EventContact contactInfo) {
        CompetitionContact competitionContact = new CompetitionContact();
        competitionContact.setContactType(contactInfo.getContactInfo().getContactsType());
        competitionContact.setContact(contactInfo.getContactInfo().getSource());
        competitionContact.setDescription(contactInfo.getDescription());
        return competitionContact;
    }

    /**
     * Метод для конвертации {@link CompetitionContact} в {@link EventContact}.
     *
     * @param competitionContact {@link CompetitionContact}
     * @return {@link EventContact}
     */
    public EventContact mapEntityToEventContact(CompetitionContact competitionContact) {
        EventContact eventContact = new EventContact();
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactsType(competitionContact.getContactType());
        contactInfo.setSource(competitionContact.getContact());
        eventContact.setContactInfo(contactInfo);
        eventContact.setDescription(competitionContact.getDescription());
        eventContact.setContactInfo(contactInfo);

        return eventContact;
    }
}
