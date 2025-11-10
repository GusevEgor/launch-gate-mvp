package com.example.core.controller;

import com.example.core.aspect.LogBefore;
import com.example.core.dto.tag.TagInfo;
import com.example.core.dto.tag.TagInfoResponse;
import com.example.core.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с тегами.
 */
@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
@Tag(name = "Котроллер для работы с тегами",
        description = "API для работы с тегами: создание, получение")
public class TagController {
    private final TagService tagService;

    /**
     * Эндпоинт для создания тега.
     *
     * @param request {@link TagInfo}
     * @return {@link TagInfoResponse}
     */
    @LogBefore
    @Operation(summary = "Создание тега")
    @PostMapping("/create")
    public TagInfoResponse createTag(TagInfo request) {
        return tagService.createTag(request);
    }

    /**
     * Эндпоинт для получения всех тегов.
     *
     * @return {@code List}{@code <}{@link TagInfoResponse}{@code >
     */
    @LogBefore
    @Operation(summary = "Получение всех тегов")
    @GetMapping("/get-all")
    public List<TagInfoResponse> getAllTags() {
        return tagService.getAllTags();
    }
}
