package ru.veucos.cms.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockAdminUserSecurityContextFactory.class)
public @interface WithMockTestUser {
    String username() default "test";

    String email() default "test@test.ru";
}
