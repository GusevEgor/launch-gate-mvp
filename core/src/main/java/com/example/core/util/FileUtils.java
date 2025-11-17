package com.example.core.util;

import com.example.core.dto.file.FileData;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@UtilityClass
public class FileUtils {

    public static ResponseEntity<Resource> createLoadResponseEntity(FileData data) {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(data.getContentType()))
                .body(data.getResource());
    }
}
