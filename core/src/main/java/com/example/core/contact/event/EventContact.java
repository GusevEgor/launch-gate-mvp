package com.example.core.contact.event;

import com.example.core.contact.ContactInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventContact {
    @Schema(name = "Контакты мероприятия")
    private ContactInfo contactInfo;
    @Schema(name = "Описание контакта мероприятия")
    private String description;
}
