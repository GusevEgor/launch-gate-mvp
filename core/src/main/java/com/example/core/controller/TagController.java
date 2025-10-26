package com.example.core.controller;

import com.example.core.dto.tag.TagInfoResponse;
import com.example.core.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/create")
    public TagInfoResponse createTag(String name) {
        return tagService.createTag(name);
    }

    @GetMapping("/get-all")
    public List<TagInfoResponse> getAllTags() {
        return tagService.getAllTags();
    }
}
