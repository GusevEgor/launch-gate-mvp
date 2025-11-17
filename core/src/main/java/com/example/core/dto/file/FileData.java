package com.example.core.dto.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;


/**
 * Дто для передачи данных файла.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileData {
    /**
     * Данные файла.
     */
    InputStreamResource resource;
    /**
     * Тип файла.
     */
    String contentType;
}
