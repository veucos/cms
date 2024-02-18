package ru.veucos.cms.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.veucos.cms.security.Role;
import ru.veucos.cms.security.jwt.JwtAuthorizationFilter;
import ru.veucos.cms.security.jwt.JwtProviderImpl;

import java.util.List;

/**
 * Конфигурация Spring Security
 */
@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    /**
     * Настройко фильтра авторизации HTTP
     *
     * @param http
     * @return - фильтр авторизации
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return http.cors(Customizer.withDefaults()) // настраиваем cors что бы работать с react
                .csrf(AbstractHttpConfigurer::disable) // отключаем csrf так как rest - stateless приложение
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        //Open API
                        .antMatchers("/api-docs/**", "/swagger*/**", "/webjars/**").permitAll()
                        //авторизаци
                        .antMatchers("/api/auth/**").permitAll()
                        //console H2
                        .antMatchers("/h2-console/**").permitAll()
                        //banks
                        .antMatchers(HttpMethod.GET, "/api/banks/**").permitAll()
                        .antMatchers("/api/banks/**").hasAnyRole(Role.ADMIN.name())
                        //users
                        .antMatchers("/api/users/**").hasAnyRole(Role.ADMIN.name())
                        .antMatchers(HttpMethod.PUT, "/api/users").hasAnyRole(Role.USER.name())
                        //offers
                        .antMatchers(HttpMethod.GET, "/api/offers/**").permitAll()
                        .antMatchers("/api/offers/**").hasAnyRole(Role.ADMIN.name())
                        //credit
                        .antMatchers(HttpMethod.GET, "/api/credit/**").hasAnyRole(Role.ADMIN.name())
                        .antMatchers("/api/credit/**").hasAnyRole(Role.USER.name())
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Определение энкодера пароля
     *
     * @return - энкодер пароля
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new BCryptPasswordEncoder();
    }

    /**
     * Конфигурация CORS
     *
     * @return - конфигурация
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080")); // указываем адрес react приложения
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Определение фильтра авторизации JWT
     *
     * @return фильтр авторизации
     */
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new JwtAuthorizationFilter(new JwtProviderImpl(JWT_SECRET, JWT_EXPIRATION_IN_MS));
    }

    /**
     * Определение менеджера авторизации
     *
     * @param http HttpSecurity
     * @return менеджер авторизации
     * @throws Exception
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
