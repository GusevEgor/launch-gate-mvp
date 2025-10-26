package com.example.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Применяет CORS ко всем эндпоинтам ("/**")
                .allowedOrigins("http://localhost:3000") // Разрешает запросы с вашего фронтенда
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Разрешает все стандартные методы
                .allowedHeaders("*") // Разрешает все заголовки (включая Authorization и Content-Type)
                .allowCredentials(true) // Разрешает отправку cookie и заголовков авторизации
                .maxAge(3600); // Кэширует preflight-ответ на 1 час
    }
}
