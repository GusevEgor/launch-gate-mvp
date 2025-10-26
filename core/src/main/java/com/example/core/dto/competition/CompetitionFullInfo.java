package com.example.core.dto.competition;

import com.example.core.dto.contact.event.EventContact;
import com.example.core.dto.manager.CompetitionManagerInfo;
import com.example.core.dto.prize.PrizeFullInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CompetitionFullInfo {
    @Schema(description = "Название конкурса")
    private String name;

    @Schema(description = "Черновик")
    private Boolean isDraft;

    @Size(min = 2, max = 2)
    @Schema(description = "Даты начала и конца регистрации")
    private List<Long> registrationDateRange;
    @Size(min = 2, max = 2)
    @Schema(description = "Даты начала и конца работы над конкурсом")
    private List<Long> competitionDateRange;
    @Size(min = 2, max = 2)
    @Schema(description = "Даты начала и конца подведения итогов и объявления результатов")
    private List<Long> resultDateRange;

    @Schema(description = "Краткое описание конкурса")
    private String shortDescription;

    @Schema(description = "Теги конкурса (по их id)")
    private List<Long> tagInfos;

    @Schema(description = "Тип соревнования")
    private CompetitionType competitionType;

    @Schema(description = "Формат проведения соревнования")
    private CompetitionFormat competitionFormat;

    @Schema(description = "Является ли конкурс публичным?")
    private Boolean isPublic;

    @Schema(description = "Минимальный и максимальный возраст участника")
    @Size(min = 2, max = 2)
    private List<Integer> participantAgeRange;

    @Schema(description = "Аудитория")
    private String targetAudience;

    @Schema(description = "Является ли соревнование командным")
    private Boolean isTeamRequired;

    @Schema(description = "Минимальный и максимальный размер команды")
    @Size(min = 2, max = 2)
    private List<Integer> teamSizeRange;

    @Schema(description = "Вся страна участвует?")
    private Boolean isCountry;

    @Schema(description = "Менеджеры проекта")
    private List<CompetitionManagerInfo> managers;

    @Schema(description = "Контакты и ссылки конкурса")
    private List<EventContact> eventContacts;

    @Schema(description = "Призы")
    private PrizeFullInfo prize;

}
