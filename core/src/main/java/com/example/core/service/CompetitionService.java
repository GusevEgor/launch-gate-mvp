package com.example.core.service;

import com.example.core.dto.competition.CompetitionFullInfo;
import com.example.core.dto.competition.response.CompetitionFullInfoResponse;
import com.example.core.dto.contact.ContactInfo;
import com.example.core.dto.contact.event.EventContact;
import com.example.core.dto.contact.manager.CompetitionManagerContactsInfo;
import com.example.core.dto.manager.CompetitionManagerInfo;
import com.example.core.dto.prize.PrizeFullInfo;
import com.example.core.dto.prize.PrizeInfo;
import com.example.core.entity.Organisation;
import com.example.core.entity.Tag;
import com.example.core.entity.competition.*;
import com.example.core.exception.NotFoundByIdException;
import com.example.core.repository.CompetitionRepository;
import com.example.core.repository.OrganisationRepository;
import com.example.core.repository.TagRepository;
import com.example.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public CompetitionFullInfoResponse createCompetition(CompetitionFullInfo request) {
        return mapCompetitionToCompetitionFullInfoResponse(
                competitionRepository.save(mapCompetitionFullInfoToEntity(request)));

    }

    public CompetitionFullInfoResponse getCompetition(Long id) {
        Competition competition = competitionRepository.findById(id).orElseThrow(
                () -> new NotFoundByIdException(Competition.class, id));
        return mapCompetitionToCompetitionFullInfoResponse(competition);
    }

    private Competition mapCompetitionFullInfoToEntity(CompetitionFullInfo request) {
        Competition competition = new Competition();
        competition.setName(request.getName());
        competition.setIsDraft(request.getIsDraft());
        competition.setFormatOfCompetition(request.getCompetitionFormat());
        competition.setCompetitionType(request.getCompetitionType());
        competition.setShortDescription(request.getShortDescription());

        competition.setCompetitionStartDate(request.getCompetitionStartDate());
        competition.setCompetitionEndDate(request.getCompetitionEndDate());
        competition.setRegistrationStartDate(request.getRegistrationStartDate());
        competition.setRegistrationEndDate(request.getRegistrationEndDate());
        competition.setResultStartDate(request.getResultStartDate());
        competition.setResultEndDate(request.getResultEndDate());

        competition.setMinParticipantAge(request.getParticipantAgeRange().getFirst());
        competition.setMaxParticipantAge(request.getParticipantAgeRange().getLast());
        competition.setIsTeamRequired(request.getIsTeamRequired());
        competition.setMinTeamSize(request.getTeamSizeRange().getFirst());
        competition.setMaxTeamSize(request.getTeamSizeRange().getLast());
        competition.setIsPublic(request.getIsPublic());
        competition.setTargetAudience(request.getTargetAudience());
        competition.setIsCountry(request.getIsCountry());

        competition.setCompetitionPrizes(request.getPrize()
                .getPrizes()
                .stream()
                .map((prizeInfo) -> {
                    CompetitionPrize competitionPrize = new CompetitionPrize();
                    competitionPrize.setCompetition(competition);
                    competitionPrize.setMedalPlace(prizeInfo.getMedalPlace());
                    competitionPrize.setTypeOfPrize(prizeInfo.getType());
                    competitionPrize.setValueOfPrize(prizeInfo.getSource());
                    return competitionPrize;
                }).collect(Collectors.toList())
        );

        competition.setCompetitionContacts(request.getEventContacts()
                .stream()
                .map((contactInfo) -> {
                    CompetitionContact competitionContact = new CompetitionContact();
                    competitionContact.setContactType(contactInfo.getContactInfo().getContactsType());
                    competitionContact.setContact(contactInfo.getContactInfo().getSource());
                    competitionContact.setDescription(contactInfo.getDescription());
                    competitionContact.setCompetition(competition);
                    return competitionContact;
                })
                .collect(Collectors.toList())
        );

        competition.setCompetitionManagers(request.getManagers()
                .stream()
                .map(managerInfo -> {
                    CompetitionManager manager = new CompetitionManager();
                    manager.setCompetition(competition);
                    manager.setRoleLabel(managerInfo.getRole());
                    manager.setIsCreator(managerInfo.getIsCreator());
                    manager.setUser(userRepository.findById(managerInfo.getUserId()).orElse(null));
                    manager.setInSystem(managerInfo.getInSystem());
                    manager.setContacts(managerInfo.getContacts()
                            .stream()
                            .map(contactInfo -> {
                                CompetitionManagerContact contact = new CompetitionManagerContact();
                                contact.setTypeOfContact(contactInfo.getContactInfo().getContactsType());
                                contact.setContact(contactInfo.getContactInfo().getSource());
                                contact.setManager(manager);
                                contact.setIsPrimary(contactInfo.getIsPrimary());
                                return contact;
                            }).collect(Collectors.toList())
                    );
                    return manager;
                }).collect(Collectors.toList())
        );

        competition.setCompetitionTags(request.getTagInfos()
                .stream()
                .map(tagId -> {
                    Tag tag = tagRepository.findById(tagId)
                            .orElseThrow(() -> new NotFoundByIdException(Tag.class, tagId));
                    CompetitionTag competitionTag = new CompetitionTag();
                    competitionTag.setTag(tag);
                    competitionTag.setCompetition(competition);
                    return competitionTag;
                })
                .collect(Collectors.toList()));

        return competition;
    }

    private CompetitionFullInfoResponse mapCompetitionToCompetitionFullInfoResponse(Competition competition) {
        CompetitionFullInfoResponse response = new CompetitionFullInfoResponse();
        response.setId(competition.getId());
        response.setName(competition.getName());
        response.setIsDraft(competition.getIsDraft());
        response.setCompetitionFormat(competition.getFormatOfCompetition());
        response.setCompetitionType(competition.getCompetitionType());
        response.setShortDescription(competition.getShortDescription());

        List<Long> registrationStartRange = new ArrayList<>();
        registrationStartRange.add(competition.getRegistrationStartDate());
        registrationStartRange.add(competition.getRegistrationEndDate());
        response.setRegistrationDateRange(registrationStartRange);

        List<Long> competitionStartRange = new ArrayList<>();
        competitionStartRange.add(competition.getCompetitionStartDate());
        competitionStartRange.add(competition.getCompetitionEndDate());
        response.setCompetitionDateRange(competitionStartRange);

        List<Long> resultStartRange = new ArrayList<>();
        resultStartRange.add(competition.getResultStartDate());
        resultStartRange.add(competition.getResultEndDate());
        response.setResultDateRange(resultStartRange);


        List<Integer> participantAgeRange = new ArrayList<>();
        participantAgeRange.add(competition.getMinParticipantAge());
        participantAgeRange.add(competition.getMaxParticipantAge());
        response.setParticipantAgeRange(participantAgeRange);

        List<Integer> teamSizeRange = new ArrayList<>();
        teamSizeRange.add(competition.getMinTeamSize());
        teamSizeRange.add(competition.getMaxTeamSize());
        response.setTeamSizeRange(teamSizeRange);

        response.setIsTeamRequired(competition.getIsTeamRequired());
        response.setIsPublic(competition.getIsPublic());
        response.setTargetAudience(competition.getTargetAudience());
        response.setIsCountry(competition.getIsCountry());

        PrizeFullInfo prizeFullInfo = new PrizeFullInfo();
        List<PrizeInfo> prizeInfos = competition.getCompetitionPrizes()
                .stream()
                .map(prizeEntity -> {
                    PrizeInfo prizeInfo = new PrizeInfo();
                    prizeInfo.setMedalPlace(prizeEntity.getMedalPlace());
                    prizeInfo.setType(prizeEntity.getTypeOfPrize());
                    prizeInfo.setSource(prizeEntity.getValueOfPrize());
                    return prizeInfo;
                })
                .collect(Collectors.toList());
        prizeFullInfo.setPrizes(prizeInfos);
        response.setPrize(prizeFullInfo);

        List<EventContact> eventContacts = competition.getCompetitionContacts()
                .stream()
                .map(competitionContact -> {
                    EventContact eventContact = new EventContact();
                    ContactInfo contactInfo = new ContactInfo();
                    contactInfo.setContactsType(competitionContact.getContactType());
                    contactInfo.setSource(competitionContact.getContact());
                    eventContact.setContactInfo(contactInfo);
                    eventContact.setDescription(competitionContact.getDescription());
                    eventContact.setContactInfo(contactInfo);
                    return eventContact;
                })
                .collect(Collectors.toList());

        response.setEventContacts(eventContacts);

        response.setManagers(competition.getCompetitionManagers()
                .stream()
                .map((manager) -> {
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
                            .map(competitionManagerContact -> {
                                ContactInfo contactInfo = new ContactInfo();
                                contactInfo.setContactsType(competitionManagerContact.getTypeOfContact());
                                contactInfo.setSource(competitionManagerContact.getContact());
                                return new CompetitionManagerContactsInfo(contactInfo, competitionManagerContact.getIsPrimary());
                            }).collect(Collectors.toList()));

                    return managerInfo;
                }).collect(Collectors.toList()));

        response.setTags(competition.getCompetitionTags()
                .stream()
                .map(
                        (tag) -> tag.getTag().getName())
                .collect(Collectors.toList()));

        return response;
    }

}