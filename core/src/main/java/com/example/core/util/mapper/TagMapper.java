package com.example.core.util.mapper;

import com.example.core.dto.tag.TagInfo;
import com.example.core.dto.tag.TagInfoResponse;
import lombok.experimental.UtilityClass;
import com.example.core.entity.Tag;

/**
 * Маппер для работы с тегами мероприятия {@link Tag}.
 */
@UtilityClass
public class TagMapper {

    /**
     * Метод для конвертации {@link TagInfo} в {@link Tag}.
     *
     * @param tagInfo {@link TagInfo}
     * @return {@link Tag}
     */
    public Tag mapTagInfoToEntity(TagInfo tagInfo) {
        Tag tag = new Tag();
        tag.setName(tagInfo.getName());
        return tag;
    }

    /**
     * Метод для конвертации {@link Tag} в {@link TagInfoResponse}.
     *
     * @param tag {@link Tag}
     * @return {@link TagInfoResponse}
     */
    public TagInfoResponse mapTagToTagInfoResponse(Tag tag) {
        TagInfoResponse tagInfo = new TagInfoResponse();
        tagInfo.setName(tag.getName());
        tagInfo.setId(tag.getId());
        return tagInfo;
    }

    /**
     * Метод для конвертации {@link Tag} в {@link TagInfo}.
     *
     * @param tag {@link Tag}
     * @return {@link TagInfo}
     */
    public TagInfo mapTagToTagInfo(Tag tag) {
        TagInfo tagInfo = new TagInfo();
        tagInfo.setName(tag.getName());
        return tagInfo;
    }
}
