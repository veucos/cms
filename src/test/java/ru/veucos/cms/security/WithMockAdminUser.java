package ru.veucos.cms.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockAdminUserSecurityContextFactory.class)
public @interface WithMockAdminUser {
    String username() default "admin";

    String email() default "admin@cms.ru";
}
