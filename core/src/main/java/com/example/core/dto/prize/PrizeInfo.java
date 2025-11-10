package com.example.core.dto.prize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrizeInfo {
    @NotNull
    @Positive
    @Schema(description = "Номер призового места")
    private Integer medalPlace;

    @NotNull
    @Schema(description = "Тип приза")
    private PrizeType type;

    @NotBlank
    @Schema(description = "Ценность")
    private String source;
}
