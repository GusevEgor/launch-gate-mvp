package com.example.core.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * Класс, содержащий аспекты для логирования.
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ProjectAspect {


    /**
     * Логирует вызов метода, привязывая первый аргумент (Long id) и второй аргумент (Object object).
     * Срабатывает только для методов, аннотированных @LogBefore И имеющих Long и Object в начале аргументов.
     */
    @Before(value = "@annotation(com.example.core.aspect.LogBefore) && args(id, object, ..)",
            argNames = "joinPoint, id, object")
    public void logBeforeFunction(JoinPoint joinPoint, Long id, Object object) {

        log.info("Calling method '{}' in class '{}' with object name '{}', with id '{}'",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType().getName(),
                object.getClass().getName(),
                id);
    }

    /**
     * Логирует вызов метода, привязывая первый аргумент к переменной 'id'.
     * Срабатывает только для методов, аннотированных @LogBefore И имеющих первый аргумент типа Long.
     */
    @Before(value = "@annotation(com.example.core.aspect.LogBefore) && args(id, ..)",
            argNames = "joinPoint, id")
    public void logBeforeFunctionWithId(JoinPoint joinPoint, Long id) {

        log.info("Calling method '{}' in class '{}' with ID: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType().getName(),
                id);
    }

    /**
     * Общая логика логирования для всех методов с аннотацией @LogBefore.
     * Кроме более специфичных
     */
    @Before(value = "@annotation(com.example.core.aspect.LogBefore) && !args(java.lang.Long, ..)")
    public void logBeforeFunctionWithId(JoinPoint joinPoint) {

        log.info("Calling method '{}' in class '{}'",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringType().getName());

    }
}