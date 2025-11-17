package com.example.core.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Дто для получения ссылки для получения файла.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Дто для получения ссылки для получения файла")
public class FileUrlResponse {
    /**
     * URL для получения файла.
     */
    @Schema(description = "URL для получения файла")
    private String fileUrl;

    /**
     * URL для удаления файла.
     */
    @Schema(description = "URL для удаления файла")
    private String deleteFileUrl;
}
