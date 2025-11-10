package com.example.core.repository;

import com.example.core.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с пользователями {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
