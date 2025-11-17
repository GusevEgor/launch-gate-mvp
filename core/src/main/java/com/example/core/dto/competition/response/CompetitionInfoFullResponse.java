package com.example.core.dto.competition.response;

import com.example.core.dto.competition.CompetitionFormat;
import com.example.core.dto.competition.CompetitionType;
import com.example.core.dto.contact.event.EventContact;
import com.example.core.dto.manager.CompetitionManagerInfo;
import com.example.core.dto.prize.PrizeFullInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionInfoFullResponse {
    @Schema(description = "Id")
    private Long id;

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

    @Schema(description = "Теги конкурса")
    private List<String> tagInfos;

    @Schema(description = "Тип соревнования")
    private CompetitionType competitionType;

    @Schema(description = "Формат проведения соревнования")
    private CompetitionFormat competitionFormat;

    @Schema(description = "Является ли конкурс публичным?")
    private Boolean isPublic;

    @Schema(description = "Минимальный и максимальный возраст участника")
    private List<Integer> participantAgeRange;

    @Schema(description = "Аудитория")
    private String targetAudience;

    @Schema(description = "Является ли соревнование командным")
    private Boolean isTeamRequired;

    @Schema(description = "Минимальный и максимальный размер команды")
    private List<Integer> teamSizeRange;

    @Schema(description = "Вся страна участвует?")
    private Boolean isCountry;

    @Schema(description = "Менеджеры проекта")
    private List<CompetitionManagerInfo> managers;

    @Schema(description = "Название организации, которая хостит конкурс")
    private String organisationName;

    @Schema(description = "Контакты и ссылки конкурса")
    private List<EventContact> eventContacts;

    @Schema(description = "Призы")
    private PrizeFullInfo prize;

    @Schema(description = "Json форма")
    private String jsonForm;

}
