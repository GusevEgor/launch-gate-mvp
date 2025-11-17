package com.example.core.controller;

import com.example.core.aspect.LogBefore;
import com.example.core.dto.file.FileUrlResponse;
import com.example.core.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.example.core.util.FileUtils.createLoadResponseEntity;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Tag(name = "Котроллер для работы с медиа",
        description = "API для работы с медиа: загрузка, получение, удаление файла")
public class FileController {

    private final FileService fileService;

    /**
     * Эндпоинт для получения ссылок на загрузку и получение медиа.
     *
     * @param file {@link MultipartFile}
     * @return {@link FileUrlResponse}
     */
    @LogBefore
    @PostMapping("/save")
    @Operation(summary = "Сохранить медиа")
    public FileUrlResponse saveFile(@RequestParam("file") MultipartFile file) {
        return fileService.saveFile(file);
    }

    /**
     * Эндпоинт для получения медиа по id.
     *
     * @param id {@link Long}
     * @return {@link ResponseEntity}{@code <}{@link Resource}{@code >}
     */
    @LogBefore
    @GetMapping("/get/{id}")
    @Operation(summary = "Получить медиа по id")
    public ResponseEntity<Resource> getFile(@PathVariable("id") Long id) {
        return createLoadResponseEntity(fileService.getFile(id));
    }

    /**
     * Эндпоинт для удаления медиа.
     *
     * @param id {@link Long}
     */
    @LogBefore
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить медиа по id")
    public void deleteFile(@PathVariable("id") Long id) {
        fileService.deleteFile(id);
    }
}