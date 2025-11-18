package com.example.core.dto.competition;

import com.example.core.dto.contact.event.EventContact;
import com.example.core.dto.manager.CompetitionManagerInfo;
import com.example.core.dto.prize.PrizeFullInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Дто для создания конкурса содержит полную информацию о конкурсе")
public class CompetitionFullInfoRequest {
    @NotBlank
    @Schema(description = "Название конкурса")
    private String name;

    @NotNull
    @Schema(description = "Является ли черновиком")
    private Boolean isDraft;

    @NotEmpty
    @Size(min = 2, max = 2)
    @Schema(description = "Даты начала и конца регистрации")
    private List<Long> registrationDateRange;

    @NotEmpty
    @Size(min = 2, max = 2)
    @Schema(description = "Даты начала и конца работы над конкурсом")
    private List<Long> competitionDateRange;

    @NotEmpty
    @Size(min = 2, max = 2)
    @Schema(description = "Даты начала и конца подведения итогов и объявления результатов")
    private List<Long> resultDateRange;

    @NotBlank
    @Schema(description = "Краткое описание конкурса")
    private String shortDescription;

    @NotNull
    @Schema(description = "Теги конкурса (по их id)")
    private List<Long> tagInfos;

    @NotNull
    @Schema(description = "Тип соревнования")
    private CompetitionType competitionType;

    @NotNull
    @Schema(description = "Формат проведения соревнования")
    private CompetitionFormat competitionFormat;

    @NotNull
    @Schema(description = "Является ли конкурс публичным")
    private Boolean isPublic;

    @NotEmpty
    @Size(min = 2, max = 2)
    @Schema(description = "Минимальный и максимальный возраст участника")
    private List<Integer> participantAgeRange;

    @NotBlank
    @Schema(description = "Аудитория")
    private String targetAudience;

    @NotNull
    @Schema(description = "Является ли соревнование командным")
    private Boolean isTeamRequired;

    @NotEmpty
    @Schema(description = "Минимальный и максимальный размер команды")
    @Size(min = 2, max = 2)
    private List<Integer> teamSizeRange;

    @NotNull
    @Schema(description = "Вся страна участвует?")
    private Boolean isCountry;

    @NotBlank
    @Schema(description = "Основное изображение конкурса")
    private String mainImageUrl;

    @NotBlank
    @Schema(description = "Json форма")
    private String jsonForm;

    @Valid
    @NotNull
    @Schema(description = "Менеджеры проекта")
    private List<CompetitionManagerInfo> managers;

    @Valid
    @NotNull
    @Schema(description = "Контакты и ссылки конкурса")
    private List<EventContact> eventContacts;

    @Valid
    @NotNull
    @Schema(description = "Призы")
    private PrizeFullInfo prize;

}
