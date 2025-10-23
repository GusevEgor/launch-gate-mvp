package com.example.core.contact;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    @Schema(description = "Тип контакта")
    private ContactType contactsType;
    @Schema(description = "Данные контакта")
    private String source;
}
