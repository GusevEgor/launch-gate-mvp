package com.example.core.dto.contact.manager;

import com.example.core.dto.contact.ContactInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionManagerContactsInfo {
    @NotNull
    @Valid
    @Schema(description = "Контакты менеджера")
    private ContactInfo contactInfo;

    @NotNull
    @Schema(description = "Является ли приоритетным")
    private Boolean isPrimary;
}
