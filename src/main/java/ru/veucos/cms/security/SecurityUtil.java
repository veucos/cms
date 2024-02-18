package ru.veucos.cms.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Утилиты авторизации и аутентификации
 */
public class SecurityUtil {

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTH_HEADER = "authorization";
    public static final String AUTH_TOKEN_TYPE = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_TYPE + " ";

    /**
     * Преобразование роли в объект SimpleGrantedAuthority
     *
     * @param role
     * @return
     */
    public static SimpleGrantedAuthority convertToAuthority(String role) {
        String formattedRole = role.startsWith(ROLE_PREFIX) ? role : ROLE_PREFIX + role;
        return new SimpleGrantedAuthority(formattedRole);
    }

    /**
     * Экстракция токена из заголовков запроса
     *
     * @param request
     * @return
     */
    public static String extractAuthTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX)) {
            //Bearer
            return bearerToken.substring(7);
        }
        return null;
    }
}
