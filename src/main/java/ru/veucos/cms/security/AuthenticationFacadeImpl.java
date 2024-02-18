package ru.veucos.cms.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Фасад для получения объекта Аутентификации
 */
@Component
@Log4j2
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public Authentication getAuthentication() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return SecurityContextHolder.getContext().getAuthentication();
    }
}