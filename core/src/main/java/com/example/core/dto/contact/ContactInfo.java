package com.example.core.dto.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    @NotNull
    @Schema(description = "Тип контакта")
    private ContactType contactsType;
    @NotBlank
    @Schema(description = "Данные контакта")
    private String source;
}
