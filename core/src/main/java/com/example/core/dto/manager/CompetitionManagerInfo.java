package com.example.core.dto.manager;

import com.example.core.dto.contact.manager.CompetitionManagerContactsInfo;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Менеджер в системе?")
    private Boolean inSystem;

    @Schema(description = "Id юзера, но может быть null, если не в системе")
    private Long userId;

    @Schema(description = "Является ли создателем конкурса")
    private Boolean isCreator;

    @Schema(description = "Роль в соревновании")
    private CompetitionManagerRole role;

    @Schema(description = "Контакты менеджера")
    private List<CompetitionManagerContactsInfo> contacts;
}
