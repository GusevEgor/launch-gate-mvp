package com.example.core.controller;

import com.example.core.aspect.LogBefore;
import com.example.core.dto.competition.CompetitionFullInfoRequest;
import com.example.core.dto.competition.response.CompetitionFullInfoResponse;
import com.example.core.service.CompetitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с мероприятиями.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/competitions")
@Tag(name = "Контроллер для работы с мероприятиями",
        description = "API для работы с мероприятиями: создание, получение")
public class CompetitionController {
    private final CompetitionService competitionService;

    /**
     * Эндпоинт для создания мероприятия.
     *
     * @param request {@link CompetitionFullInfoRequest}
     * @return {@link CompetitionFullInfoResponse}
     */
    @LogBefore
    @PostMapping("create")
    @Operation(summary = "Создание мероприятия")
    public CompetitionFullInfoResponse createCompetition(@RequestBody CompetitionFullInfoRequest request) {
        return competitionService.createCompetition(request);
    }

    /**
     * Эндпоинт для получения мероприятия по id.
     *
     * @param id {@link Long}
     * @return {@link CompetitionFullInfoResponse}
     */
    @LogBefore
    @GetMapping("get/{id}")
    @Operation(summary = "Получение мероприятия по id")
    public CompetitionFullInfoResponse createCompetition(@PathVariable Long id) {
        return competitionService.getCompetition(id);
    }

    /**
     * Эндпоинт для получения списка мероприятий c пагинацией.
     *
     * @param page   {@link Integer} текущая страница
     * @param size   {@link Integer} количество элементов на странице
     * @param search {@link String} строка поиска
     * @return {@link List}{@code <}{@link CompetitionFullInfoResponse}{@code >}
     */
    @LogBefore
    @GetMapping("get-all")
    @Operation(summary = "Получение списка мероприятий с пагинацией")
    public List<CompetitionFullInfoResponse> getAllCompetition(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "search", defaultValue = "") String search) {
        return competitionService.getAllCompetition(page, size, search);
    }

    /**
     * Удаление мероприятия по id.
     *
     * @param id {@link Long}
     */
    @LogBefore
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Удаление мероприятия по id")
    public void deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
    }


}

