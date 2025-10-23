package com.example.core.prize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrizeInfo {
    @Schema(description = "Номер призового места")
    private Integer medalPlace;
    @Schema(description = "Тип приза")
    private PrizeType type;
    @Schema(description = "Ценность")
    private String source;
}
