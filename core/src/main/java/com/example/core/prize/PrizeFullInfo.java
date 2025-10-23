package com.example.core.prize;

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
public class PrizeFullInfo {
    @Schema(description = "Описание призов")
    private String description;
    @Schema(description = "Призы")
    private List<PrizeInfo> prizes;
}
