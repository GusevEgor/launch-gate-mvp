package com.example.core.util.competition;

import com.example.core.dto.prize.PrizeInfo;
import com.example.core.entity.competition.CompetitionPrize;
import lombok.experimental.UtilityClass;

/**
 * Маппер для работы с призами мероприятия {@link CompetitionPrize}.
 */
@UtilityClass
public class CompetitionPrizeMapper {

    /**
     * Метод для конвертации {@link PrizeInfo} в {@link CompetitionPrize}.
     *
     * @param prizeInfo {@link PrizeInfo}
     * @return {@link CompetitionPrize}
     */
    public CompetitionPrize mapPrizeInfoToEntity(PrizeInfo prizeInfo) {
        CompetitionPrize competitionPrize = new CompetitionPrize();
        competitionPrize.setMedalPlace(prizeInfo.getMedalPlace());
        competitionPrize.setTypeOfPrize(prizeInfo.getType());
        competitionPrize.setValueOfPrize(prizeInfo.getSource());
        return competitionPrize;
    }

    /**
     * Метод для конвертации {@link CompetitionPrize} в {@link PrizeInfo}.
     *
     * @param competitionPrize {@link CompetitionPrize}
     * @return {@link PrizeInfo}
     */
    public PrizeInfo mapPrizeEntityToInfo(CompetitionPrize competitionPrize) {
        PrizeInfo prizeInfo = new PrizeInfo();
        prizeInfo.setMedalPlace(competitionPrize.getMedalPlace());
        prizeInfo.setType(competitionPrize.getTypeOfPrize());
        prizeInfo.setSource(competitionPrize.getValueOfPrize());
        return prizeInfo;
    }
}
