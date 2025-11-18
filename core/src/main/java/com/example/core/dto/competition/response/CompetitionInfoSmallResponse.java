package com.example.core.dto.competition.response;

import com.example.core.dto.competition.CompetitionFormat;
import com.example.core.dto.competition.CompetitionType;
import com.example.core.dto.prize.PrizeFullInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionInfoSmallResponse {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Название мероприятия")
    private String name;

    @Schema(description = "Дата начала регистрации и подведения результатов")
    private List<Long> competitionDateRange;

    @Schema(description = "Теги мероприятия")
    private List<String> tagInfos;

    @Schema(description = "Тип соревнования")
    private CompetitionType competitionType;

    @Schema(description = "Формат проведения соревнования")
    private CompetitionFormat competitionFormat;

    @Schema(description = "Является ли конкурс публичным")
    private Boolean isPublic;

    @Schema(description = "Целевая аудитория")
    private String targetAudience;

    @Schema(description = "Является ли соревнование командным")
    private Boolean isTeamRequired;

    @Schema(description = "Размер команды")
    private List<Integer> teamSizeRange;

    @Schema(description = "Призы")
    private PrizeFullInfo prize;

    @Schema(description = "Основное изображение конкурса")
    private String mainImageUrl;
}

