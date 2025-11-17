package com.example.core.repository;

import com.example.core.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByFileName(String objectKey);

    void deleteByFileName(String fileName);
}
