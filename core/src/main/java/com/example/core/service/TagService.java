package com.example.core.service;

import com.example.core.dto.tag.TagInfo;
import com.example.core.dto.tag.TagInfoResponse;
import com.example.core.entity.Tag;
import com.example.core.repository.TagRepository;
import com.example.core.util.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с тегами мероприятия {@link Tag}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /**
     * Метод для создания тега.
     *
     * @param request {@link TagInfo}
     * @return {@link TagInfoResponse}
     */
    public TagInfoResponse createTag(TagInfo request) {
        Tag tag = TagMapper.mapTagInfoToEntity(request);
        Tag savedTag = tagRepository.save(tag);

        log.info("Tag with id {} created", savedTag.getId());
        return new TagInfoResponse(tag.getId(), tag.getName());
    }

    /**
     * Метод для получения всех тегов.
     *
     * @return {@code List}{@code <}{@link TagInfoResponse}{@code >}
     */
    public List<TagInfoResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(TagMapper::mapTagToTagInfoResponse)
                .toList();
    }
}
