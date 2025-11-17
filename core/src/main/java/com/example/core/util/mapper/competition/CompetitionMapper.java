package com.example.core.util.mapper.competition;

import com.example.core.dto.competition.CompetitionFullInfoRequest;
import com.example.core.dto.competition.response.CompetitionInfoFullResponse;
import com.example.core.dto.competition.response.CompetitionInfoSmallResponse;
import com.example.core.dto.contact.event.EventContact;
import com.example.core.dto.prize.PrizeFullInfo;
import com.example.core.dto.prize.PrizeInfo;
import com.example.core.entity.competition.Competition;
import com.example.core.entity.competition.CompetitionContact;
import com.example.core.entity.competition.CompetitionPrize;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Маппер для работы с мероприятиями {@link Competition}.
 */
@UtilityClass
public class CompetitionMapper {

    /**
     * Метод для конвертации {@link CompetitionFullInfoRequest} в {@link Competition}.
     *
     * @param request {@link CompetitionFullInfoRequest}
     * @return {@link Competition}
     */
    public Competition mapCompetitionFullInfoRequestToEntity(CompetitionFullInfoRequest request) {
        Competition competition = new Competition();
        //Устанавливаем основные поля
        competition.setName(request.getName());
        competition.setIsDraft(request.getIsDraft());
        competition.setFormatOfCompetition(request.getCompetitionFormat());
        competition.setCompetitionType(request.getCompetitionType());
        competition.setShortDescription(request.getShortDescription());

        competition.setCompetitionStartDate(request.getCompetitionDateRange().getFirst());
        competition.setCompetitionEndDate(request.getCompetitionDateRange().getLast());
        competition.setRegistrationStartDate(request.getRegistrationDateRange().getFirst());
        competition.setRegistrationEndDate(request.getRegistrationDateRange().getLast());
        competition.setResultStartDate(request.getResultDateRange().getFirst());
        competition.setResultEndDate(request.getResultDateRange().getLast());

        competition.setMinParticipantAge(request.getParticipantAgeRange().getFirst());
        competition.setMaxParticipantAge(request.getParticipantAgeRange().getLast());
        competition.setIsTeamRequired(request.getIsTeamRequired());
        competition.setMinTeamSize(request.getTeamSizeRange().getFirst());
        competition.setMaxTeamSize(request.getTeamSizeRange().getLast());
        competition.setIsPublic(request.getIsPublic());
        competition.setTargetAudience(request.getTargetAudience());
        competition.setIsCountry(request.getIsCountry());
        competition.setPrizeDescription(request.getPrize().getDescription());

        // Устанавливаем награды
        competition.setCompetitionPrizes(request.getPrize()
                .getPrizes()
                .stream()
                .map((prizeInfo) -> {
                    CompetitionPrize competitionPrize = CompetitionPrizeMapper.mapPrizeInfoToEntity(prizeInfo);
                    competitionPrize.setCompetition(competition);
                    return competitionPrize;
                }).collect(Collectors.toList())
        );

        // Устанавливаем контакты мероприятия
        competition.setCompetitionContacts(request.getEventContacts()
                .stream()
                .map((contactInfo) -> {
                    CompetitionContact competitionContact =
                            CompetitionContactMapper.mapEventContactToEntity(contactInfo);
                    competitionContact.setCompetition(competition);
                    return competitionContact;
                })
                .collect(Collectors.toList())
        );

        return competition;
    }

    /**
     * Метод для конвертации {@link Competition} в {@link CompetitionInfoFullResponse}.
     *
     * @param competition {@link Competition}
     * @return {@link CompetitionInfoFullResponse}
     */
    public CompetitionInfoFullResponse mapEntityToCompetitionFullInfoResponse(Competition competition) {
        CompetitionInfoFullResponse response = new CompetitionInfoFullResponse();
        //Установливаем основные поля
        response.setId(competition.getId());
        response.setName(competition.getName());
        response.setIsDraft(competition.getIsDraft());
        response.setCompetitionFormat(competition.getFormatOfCompetition());
        response.setCompetitionType(competition.getCompetitionType());
        response.setShortDescription(competition.getShortDescription());

        // Устанавливаем даты регистрации (начало и конец)
        List<Long> registrationStartRange = new ArrayList<>();
        registrationStartRange.add(competition.getRegistrationStartDate());
        registrationStartRange.add(competition.getRegistrationEndDate());
        response.setRegistrationDateRange(registrationStartRange);

        // Устанавливаем даты проведения мероприятия (начало и конец)
        List<Long> competitionStartRange = new ArrayList<>();
        competitionStartRange.add(competition.getCompetitionStartDate());
        competitionStartRange.add(competition.getCompetitionEndDate());
        response.setCompetitionDateRange(competitionStartRange);

        // Устанавливаем даты подведения результатов (начало и конец)
        List<Long> resultStartRange = new ArrayList<>();
        resultStartRange.add(competition.getResultStartDate());
        resultStartRange.add(competition.getResultEndDate());
        response.setResultDateRange(resultStartRange);

        // Устанавливаем возврастные ограничения (минимальный и максимальный возраст)
        List<Integer> participantAgeRange = new ArrayList<>();
        participantAgeRange.add(competition.getMinParticipantAge());
        participantAgeRange.add(competition.getMaxParticipantAge());
        response.setParticipantAgeRange(participantAgeRange);

        // Устанавливаем размеры команд (минимальный и максимальный размер)
        List<Integer> teamSizeRange = new ArrayList<>();
        teamSizeRange.add(competition.getMinTeamSize());
        teamSizeRange.add(competition.getMaxTeamSize());
        response.setTeamSizeRange(teamSizeRange);

        response.setIsTeamRequired(competition.getIsTeamRequired());
        response.setIsPublic(competition.getIsPublic());
        response.setTargetAudience(competition.getTargetAudience());
        response.setIsCountry(competition.getIsCountry());

        // Устанавливаем награды
        PrizeFullInfo prizeFullInfo = new PrizeFullInfo();
        List<PrizeInfo> prizeInfos = competition.getCompetitionPrizes()
                .stream()
                .map(CompetitionPrizeMapper::mapPrizeEntityToInfo)
                .collect(Collectors.toList());
        prizeFullInfo.setPrizes(prizeInfos);
        prizeFullInfo.setDescription(competition.getPrizeDescription());
        response.setPrize(prizeFullInfo);

        // Устанавливаем контакты мероприятия
        List<EventContact> eventContacts = competition.getCompetitionContacts()
                .stream()
                .map(CompetitionContactMapper::mapEntityToEventContact)
                .collect(Collectors.toList());

        response.setEventContacts(eventContacts);

        // Устанавливаем менеджеров
        response.setManagers(competition.getCompetitionManagers()
                .stream()
                .map(CompetitionManagerMapper::mapEntityToCompetitionManagerInfo)
                .collect(Collectors.toList()));

        // Устанавливаем теги
        response.setTagInfos(competition.getCompetitionTags()
                .stream()
                .map((tag) -> tag.getTag().getName())
                .collect(Collectors.toList()));

        return response;
    }

    /**
     * Метод для конвертации {@link Competition} в {@link CompetitionInfoSmallResponse}.
     *
     * @param competition {@link Competition}
     * @return {@link CompetitionInfoSmallResponse}
     */
    public CompetitionInfoSmallResponse mapEntityToCompetitionSmallInfoResponse(Competition competition) {
        CompetitionInfoSmallResponse response = new CompetitionInfoSmallResponse();
        response.setId(competition.getId());
        response.setName(competition.getName());
        response.setCompetitionDateRange(List.of(competition.getRegistrationStartDate(), competition.getResultEndDate()));

        response.setTagInfos(competition.getCompetitionTags()
                .stream()
                .map((tag) -> tag.getTag().getName())
                .collect(Collectors.toList()));

        response.setCompetitionType(competition.getCompetitionType());
        response.setCompetitionFormat(competition.getFormatOfCompetition());
        response.setIsPublic(competition.getIsPublic());
        response.setTargetAudience(competition.getTargetAudience());
        response.setIsTeamRequired(competition.getIsTeamRequired());

        List<Integer> teamSizeRange = new ArrayList<>();
        teamSizeRange.add(competition.getMinTeamSize());
        teamSizeRange.add(competition.getMaxTeamSize());
        response.setTeamSizeRange(teamSizeRange);

        PrizeFullInfo prizeFullInfo = new PrizeFullInfo();
        List<PrizeInfo> prizeInfos = competition.getCompetitionPrizes()
                .stream()
                .map(CompetitionPrizeMapper::mapPrizeEntityToInfo)
                .collect(Collectors.toList());
        prizeFullInfo.setDescription(competition.getPrizeDescription());
        prizeFullInfo.setPrizes(prizeInfos);
        response.setPrize(prizeFullInfo);

        return response;
    }

}
