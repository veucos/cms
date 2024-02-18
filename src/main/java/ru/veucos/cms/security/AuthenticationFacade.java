package ru.veucos.cms.security;

import org.springframework.security.core.Authentication;

/**
 * Фасад для получения объекта Аутентификации
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
}
