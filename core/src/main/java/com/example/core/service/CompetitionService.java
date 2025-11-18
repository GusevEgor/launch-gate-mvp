package com.example.core.service;

import com.example.core.dto.competition.CompetitionFullInfoRequest;
import com.example.core.dto.competition.response.CompetitionInfoFullResponse;
import com.example.core.dto.competition.response.CompetitionInfoSmallResponse;
import com.example.core.entity.Tag;
import com.example.core.entity.competition.*;
import com.example.core.exception.NotFoundByIdException;
import com.example.core.repository.CompetitionRepository;
import com.example.core.repository.TagRepository;
import com.example.core.repository.UserRepository;
import com.example.core.util.mapper.competition.CompetitionManagerMapper;
import com.example.core.util.mapper.competition.CompetitionMapper;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с мероприятиями.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final FileService fileService;

    /**
     * Метод для создания мероприятия.
     *
     * @param request {@link CompetitionFullInfoRequest}
     * @return {@link CompetitionInfoFullResponse}
     */
    @Transactional
    public CompetitionInfoFullResponse createCompetition(CompetitionFullInfoRequest request) {
        Competition competition = CompetitionMapper.mapCompetitionFullInfoRequestToEntity(request);
        competition.setShortDescriptionName(fileService.saveJsonString(request.getShortDescription()));
        competition.setPrizeDescriptionName(fileService.saveJsonString(request.getPrize().getDescription()));

        // Устанавливаем менеджеров
        competition.setCompetitionManagers(request.getManagers()
                .stream()
                .map(managerInfo -> {
                    CompetitionManager manager =
                            CompetitionManagerMapper.mapCompetitionManagerInfoToEntity(managerInfo);
                    manager.setCompetition(competition);
                    manager.setUser(userRepository.findById(managerInfo.getUserId()).orElse(null));
                    return manager;
                })
                .collect(Collectors.toList())
        );

        // Устанавливаем теги мероприятия
        competition.setCompetitionTags(request.getTagInfos()
                .stream()
                .map(tagId -> {
                    Tag tag = tagRepository.findById(tagId)
                            .orElseThrow(() -> new NotFoundByIdException(Tag.class, tagId));
                    CompetitionTag competitionTag = new CompetitionTag();
                    competitionTag.setTag(tag);
                    competitionTag.setCompetition(competition);
                    return competitionTag;
                })
                .collect(Collectors.toList()));

        Competition savedCompetition = competitionRepository.save(competition);

        log.info("Competition with id {} created", savedCompetition.getId());

        CompetitionInfoFullResponse response =
                CompetitionMapper.mapEntityToCompetitionFullInfoResponse(savedCompetition);
        response.setShortDescription(request.getShortDescription());
        response.getPrize().setDescription(request.getPrize().getDescription());
        return response;

    }

    /**
     * Метод получения мероприятия по id.
     *
     * @param id {@link Long}
     * @return {@link CompetitionInfoFullResponse}
     */
    public CompetitionInfoFullResponse getCompetition(Long id) {
        Competition competition = competitionRepository.findById(id).orElseThrow(
                () -> new NotFoundByIdException(Competition.class, id));
        CompetitionInfoFullResponse response = CompetitionMapper.mapEntityToCompetitionFullInfoResponse(competition);
        response.setShortDescription(fileService.getJsonString(competition.getShortDescriptionName()));
        response.getPrize().setDescription(fileService.getJsonString(competition.getPrizeDescriptionName()));
        return response;
    }

    /**
     * Метод получения списка мероприятий с паганицией.
     *
     * @param page   {@link Integer}
     * @param size   {@link Integer}
     * @param search {@link String}
     * @return {@link List}{@code <}{@link CompetitionInfoFullResponse}{@code >}
     */
    public List<CompetitionInfoSmallResponse> getAllCompetition(Integer page, Integer size, String search) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<Competition> spec = (root, query, criteriaBuilder) -> {
            if (search != null && !search.trim().isEmpty()) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + search.toLowerCase().trim() + "%"
                );
            }

            return criteriaBuilder.conjunction();
        };

        List<CompetitionInfoSmallResponse> listCompetition = competitionRepository.findAll(spec, pageable)
                .map(competition -> {
                    CompetitionInfoSmallResponse response =
                            CompetitionMapper.mapEntityToCompetitionSmallInfoResponse(competition);
                    response.getPrize().setDescription(fileService.getJsonString(competition.getPrizeDescriptionName()));
                    return response;
                })
                .getContent();

        return listCompetition;
    }

    /**
     * Метод удаления мероприятия по id.
     *
     * @param id {@link Long}
     */
    public void deleteCompetition(Long id) {
        Competition competition = competitionRepository.findById(id).orElseThrow(
                () -> new NotFoundByIdException(Competition.class, id));

        competitionRepository.deleteById(id);
        fileService.deleteJsonFile(competition.getShortDescriptionName());
        fileService.deleteJsonFile(competition.getPrizeDescriptionName());
        log.info("Competition with id {} deleted", id);
    }

}