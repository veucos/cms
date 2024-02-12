package ru.veucos.cms.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.veucos.cms.security.CustomUserPrincipal;
import ru.veucos.cms.security.SecurityUtil;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Log
public class JwtProviderImpl implements JwtProvider {

    //@Value("${app.jwt.secret}")
    private final String JWT_SECRET;

    //@Value("${app.jwt.expiration-in-ms}")
    private final Long JWT_EXPIRATION_IN_MS;

    public JwtProviderImpl(@Value("${app.jwt.secret}") String JWT_SECRET, @Value("${app.jwt.expiration-in-ms}") Long JWT_EXPIRATION_IN_MS) {
        this.JWT_SECRET = JWT_SECRET;
        this.JWT_EXPIRATION_IN_MS = JWT_EXPIRATION_IN_MS;
    }

    @PostConstruct
    public void init() {
        log.info("init JWT: " + JWT_SECRET);
        log.info("init JWT_EXPIRATION_IN_MS: " + JWT_EXPIRATION_IN_MS);
    }

    @Override
    public String generateToken(CustomUserPrincipal auth) {
        log.info("generateToken JWT: " + JWT_SECRET);
        log.info("generateToken JWT_EXPIRATION_IN_MS: " + JWT_EXPIRATION_IN_MS);


        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        //need a key to encryption
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().subject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key)
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        Claims claims = extractClaims(request);
        log.warning("getAuthentication claims: " + claims);
        if (claims == null) {
            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtil::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = CustomUserPrincipal.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();


        if (username == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }


    @Override
    public boolean isTokenValid(HttpServletRequest request) {
        Claims claims = extractClaims(request);

        if (claims == null) {
            return false;
        }

        //return !claims.getExpiration().before(new Date())
        return !claims.getExpiration().before(new Date());
    }

    public Claims extractClaims(HttpServletRequest request) {
        log.info("extractClaims JWT: " + JWT_SECRET);
        log.info("extractClaims JWT_EXPIRATION_IN_MS: " + JWT_EXPIRATION_IN_MS);

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
