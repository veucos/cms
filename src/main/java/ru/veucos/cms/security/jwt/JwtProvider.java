package ru.veucos.cms.security.jwt;

import org.springframework.security.core.Authentication;
import ru.veucos.cms.security.CustomUserPrincipal;

import javax.servlet.http.HttpServletRequest;

/**
 * Провайдер JWT (интерфейс)
 */
public interface JwtProvider {
    /**
     * Генерация токена JWT
     *
     * @param auth авторизационные данные
     * @return
     */
    String generateToken(CustomUserPrincipal auth);

    /**
     * Получение объекта аутентификации
     *
     * @param request запрос
     * @return
     */
    Authentication getAuthentication(HttpServletRequest request);

    /**
     * Проверка токена JWT
     *
     * @param request
     * @return
     */
    boolean isTokenValid(HttpServletRequest request);
}
