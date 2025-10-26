package com.example.core.controller;

import com.example.core.dto.competition.CompetitionFullInfo;
import com.example.core.dto.competition.response.CompetitionFullInfoResponse;
import com.example.core.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*

получение всех организаций, тегов

теги - id, название
организация - id, название
гет списка соревнований с пагинацией
*/


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;


    @PostMapping("create")
    public CompetitionFullInfoResponse createCompetition(@RequestBody CompetitionFullInfo request) {
        return competitionService.createCompetition(request);
    }

    @GetMapping("get/{id}")
    public CompetitionFullInfoResponse createCompetition(@PathVariable Long id) {
        return competitionService.getCompetition(id);
    }

    @GetMapping("get-all")
    public List<CompetitionFullInfoResponse> getAllCompetition(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            @RequestParam(value = "search", defaultValue = "") String search
    ) {
        return competitionService.getAllCompetition(page, size, search);
    }


}

