package com.example.core.service;

import com.example.core.dto.competition.CompetitionFullInfoRequest;
import com.example.core.dto.competition.response.CompetitionFullInfoResponse;
import com.example.core.entity.Tag;
import com.example.core.entity.competition.*;
import com.example.core.exception.NotFoundByIdException;
import com.example.core.repository.CompetitionRepository;
import com.example.core.repository.TagRepository;
import com.example.core.repository.UserRepository;
import com.example.core.util.competition.CompetitionManagerMapper;
import com.example.core.util.competition.CompetitionMapper;
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

    /**
     * Метод для создания мероприятия.
     *
     * @param request {@link CompetitionFullInfoRequest}
     * @return {@link CompetitionFullInfoResponse}
     */
    @Transactional
    public CompetitionFullInfoResponse createCompetition(CompetitionFullInfoRequest request) {
        Competition competition = CompetitionMapper.mapCompetitionFullInfoRequestToEntity(request);

        // Устанавливаем менеджеров
        competition.setCompetitionManagers(request.getManagers()
                .stream()
                .map(managerInfo -> {
                    CompetitionManager manager =
                            CompetitionManagerMapper.mapCompetitionManagerInfoToEntity(managerInfo);
                    manager.setCompetition(competition);
                    manager.setUser(userRepository.findById(managerInfo.getUserId()).orElse(null));
                    return manager;
                }).collect(Collectors.toList())
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
        return CompetitionMapper.mapEntityToCompetitionFullInfoResponse(savedCompetition);

    }

    /**
     * Метод получения мероприятия по id.
     *
     * @param id {@link Long}
     * @return {@link CompetitionFullInfoResponse}
     */
    public CompetitionFullInfoResponse getCompetition(Long id) {
        Competition competition = competitionRepository.findById(id).orElseThrow(
                () -> new NotFoundByIdException(Competition.class, id));
        return CompetitionMapper.mapEntityToCompetitionFullInfoResponse(competition);
    }

    /**
     * Метод получения списка мероприятий с паганицией.
     *
     * @param page   {@link Integer}
     * @param size   {@link Integer}
     * @param search {@link String}
     * @return {@link List}{@code <}{@link CompetitionFullInfoResponse}{@code >}
     */
    public List<CompetitionFullInfoResponse> getAllCompetition(Integer page, Integer size, String search) {

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


        return competitionRepository.findAll(spec, pageable)
                .map(CompetitionMapper::mapEntityToCompetitionFullInfoResponse)
                .getContent();
    }

    /**
     * Метод удаления мероприятия по id.
     *
     * @param id {@link Long}
     */
    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
        log.info("Competition with id {} deleted", id);
    }

}