package com.example.core.service;

import com.example.core.dto.tag.TagInfoResponse;
import com.example.core.entity.Tag;
import com.example.core.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public TagInfoResponse createTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tagRepository.save(tag);
        return new TagInfoResponse(tag.getId(), tag.getName());
    }

    public List<TagInfoResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tag -> new TagInfoResponse(tag.getId(), tag.getName()))
                .toList();
    }
}
