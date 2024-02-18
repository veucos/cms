package ru.veucos.cms.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Set;

public class WithMockTestUserSecurityContextFactory
        implements WithSecurityContextFactory<WithMockTestUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockTestUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        CustomUserPrincipal principal = CustomUserPrincipal.builder()
                .id(1L)
                .email(customUser.email())
                .authorities(Set.of(SecurityUtil.convertToAuthority("ADMIN")))
                .build();
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
