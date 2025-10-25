package com.example.core.controller;

import com.example.core.dto.competition.CompetitionFullInfo;
import com.example.core.dto.competition.response.CompetitionFullInfoResponse;
import com.example.core.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity createCompetition(@RequestBody CompetitionFullInfo request) {
        return ResponseEntity.ok(competitionService.createCompetition(request));
    }

    @GetMapping("get/{id}")
    public CompetitionFullInfoResponse createCompetition(@PathVariable Long id) {
        return competitionService.getCompetition(id);
    }


}

