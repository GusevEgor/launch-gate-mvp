package com.example.core.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Дто для генерации ссылки для загрузки файла.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Дто для генерации ссылки для загрузки файла")
public class GenerateUrlRequest {
    /**
     * Имя файла.
     */
    @Schema(description = "Имя файла", example = "test.txt")
    private String fileName;
}
