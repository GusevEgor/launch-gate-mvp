package com.example.core.competition;

import com.example.core.manager.CompetitionManagerInfo;
import com.example.core.tag.TagInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Дата начала регистрации")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationStartDate;

    @Schema(description = "Дата конца регистрации")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationEndDate;

    @Schema(description = "Дата начала работы над конкурсом")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime competitionStartDate;

    @Schema(description = "Дата конца работы над конкурсом")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime competitionEndDate;

    @Schema(description = "Дата начала подведения итогов и объявления результатов")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resultStartDate;

    @Schema(description = "Дата конца подведения итогов и объявления результатов")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resultEndDate;

    @Schema(description = "Краткое описание конкурса")
    private String shortDescription;

    @Schema(description = "Теги конкурса")
    private List<TagInfo> tagInfos;

    @Schema(description = "Timezone")
    private String timeZone;

    @Schema(description = "Id организации, которая хостит конкурс")
    private Long hostId;

    @Schema(description = "Тип соревнования")
    private CompetitionType competitionType;

    @Schema(description = "Менеджеры проекта")
    private List<CompetitionManagerInfo> managers;

    @Schema(description = "Является ли конкурс публичным?")
    private Boolean isPublic;

    @Schema(description = "Минимальный возраст участника")
    private Integer minParticipantAge;

    @Schema(description = "Максимальный возраст участника")
    private Integer maxParticipantAge;

    @Schema(description = "Аудитория")
    private String targetAudience;

    @Schema(description = "Является ли соревнование командным")
    private Boolean isTeamRequired;

    @Schema(description = "Минимальный размер команды")
    private Integer minTeamSize;

    @Schema(description = "Максимальный размер команды")
    private Integer maxTeamSize;

    @Schema(description = "Вся страна участвует?")
    private boolean isCounty;

}
