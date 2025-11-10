package com.example.core.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Обертка для ответов API, использующих пагинацию.
 *
 * @param <T> Тип контента
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {
    /**
     * Контент.
     */
    @Schema(description = "Список элементов на текущей странице")
    private List<T> content;

    /**
     * Общее количество элементов.
     */
    @Schema(description = "Общее количество элементов во всех страницах", example = "100")
    private Long totalElements;

    /**
     * Общее количество страниц.
     */
    @Schema(description = "Общее количество страниц", example = "10")
    private Integer totalPages;

    /**
     * Номер текущей страницы.
     */
    @Schema(description = "Номер текущей страницы (начиная с 0)", example = "0")
    private Integer currentPage;

    /**
     * Количество элементов на странице.
     */
    @Schema(description = "Количество элементов на странице", example = "10")
    private Integer pageSize;

    /**
     * Конструктор-преобразователь.
     * Принимает {@link Page} и автоматически заполняет поля {@link PagedResponse}.
     *
     * @param page {@link Page}
     */
    public PagedResponse(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.pageSize = page.getSize();
    }

}


