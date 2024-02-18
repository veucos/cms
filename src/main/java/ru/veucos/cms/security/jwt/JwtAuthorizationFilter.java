package ru.veucos.cms.security.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр авторизации JWT
 */
@AllArgsConstructor
@Log4j2
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    /**
     * Фильтр аутентификации
     *
     * @param request     запрос
     * @param response    ответ
     * @param filterChain цепочка фильтров
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = jwtProvider.getAuthentication(request);
        log.info("JwtAuthorizationFilter authentication: " + authentication);
        if (authentication != null && jwtProvider.isTokenValid(request)) {
            log.info("JwtAuthorizationFilter jwtProvider.isTokenValid(request): " + jwtProvider.isTokenValid(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
