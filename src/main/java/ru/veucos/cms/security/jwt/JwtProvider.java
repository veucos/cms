package ru.veucos.cms.security.jwt;

import org.springframework.security.core.Authentication;
import ru.veucos.cms.security.CustomUserPrincipal;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(CustomUserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
