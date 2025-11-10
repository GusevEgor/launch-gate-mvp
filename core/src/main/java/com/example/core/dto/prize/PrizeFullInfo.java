package com.example.core.dto.prize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class PrizeFullInfo {
    @NotBlank
    @Schema(description = "Описание приза")
    private String description;

    @NotEmpty
    @Valid
    @Schema(description = "Призы")
    private List<PrizeInfo> prizes;
}
