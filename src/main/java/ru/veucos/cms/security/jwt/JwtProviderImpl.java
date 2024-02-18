package ru.veucos.cms.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.veucos.cms.security.CustomUserPrincipal;
import ru.veucos.cms.security.SecurityUtil;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Провайдер JWT
 */
@Component
@Log4j2
public class JwtProviderImpl implements JwtProvider {

    private final String JWT_SECRET;
    private final Long JWT_EXPIRATION_IN_MS;

    /**
     * Конструктор
     *
     * @param JWT_SECRET
     * @param JWT_EXPIRATION_IN_MS
     */
    public JwtProviderImpl(@Value("${app.jwt.secret}") String JWT_SECRET, @Value("${app.jwt.expiration-in-ms}") Long JWT_EXPIRATION_IN_MS) {
        this.JWT_SECRET = JWT_SECRET;
        this.JWT_EXPIRATION_IN_MS = JWT_EXPIRATION_IN_MS;
    }

    /**
     * Генерация токена JWT
     *
     * @param auth авторизационные данные
     * @return
     */
    @Override
    public String generateToken(CustomUserPrincipal auth) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        log.info("generateToken JWT: " + JWT_SECRET);
        log.info("generateToken JWT_EXPIRATION_IN_MS: " + JWT_EXPIRATION_IN_MS);
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().subject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key)
                .compact();
    }

    /**
     * Получение объекта аутентификации
     *
     * @param request запрос
     * @return
     */
    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        Claims claims = extractClaims(request);
        if (claims == null) {
            return null;
        }

        String email = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtil::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = CustomUserPrincipal.builder()
                .email(email)
                .authorities(authorities)
                .id(userId)
                .build();


        if (email == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    /**
     * Проверка токена JWT
     *
     * @param request
     * @return
     */
    @Override
    public boolean isTokenValid(HttpServletRequest request) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        Claims claims = extractClaims(request);
        if (claims == null) {
            return false;
        }
        return !claims.getExpiration().before(new Date());
    }

    private Claims extractClaims(HttpServletRequest request) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        String token = SecurityUtil.extractAuthTokenFromRequest(request);
        if (token == null) {
            return null;
        }
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
