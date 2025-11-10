package com.example.core.dto.manager;

import com.example.core.dto.contact.manager.CompetitionManagerContactsInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionManagerInfo {
    @NotNull
    @Schema(description = "Менеджер в системе?")
    private Boolean inSystem;

    @Schema(description = "Id юзера, но может быть null, если не в системе")
    private Long userId;

    @NotNull
    @Schema(description = "Является ли создателем конкурса")
    private Boolean isCreator;

    @NotNull
    @Schema(description = "Роль в соревновании")
    private CompetitionManagerRole role;

    @Valid
    @NotNull
    @Schema(description = "Контакты менеджера")
    private List<CompetitionManagerContactsInfo> contacts;
}
