package com.example.core.dto.contact.event;

import com.example.core.dto.contact.ContactInfo;
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
    private ContactInfo contactInfo;
    @Schema(name = "Описание контакта мероприятия")
    private String description;
}
