package com.example.core.repository;

import com.example.core.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с тегами {@}.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
}
