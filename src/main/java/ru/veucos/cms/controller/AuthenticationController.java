package ru.veucos.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.veucos.cms.dto.AuthUserDto;
import ru.veucos.cms.dto.UserDto;

import javax.validation.Valid;

/**
 * Контроллер авторизации (интерфейс)
 */
public interface AuthenticationController {
    /**
     * Запрос регистрации
     *
     * @param user данные пользователя
     * @return - респонс с данными пользователя
     */
    @PostMapping("signup")
    @Operation(summary = "Регистрация")
    ResponseEntity<UserDto> signUp(@RequestBody @Valid UserDto user);

    /**
     * Запрос авторизации
     *
     * @param user - пользователь, пароль
     * @return - респонс с данными пользователя
     */
    @PostMapping("signin")
    @Operation(summary = "Авторизация")
    ResponseEntity<UserDto> signIn(@RequestBody @Valid AuthUserDto user);

    /**
     * Запрос получения текущего(авторизованного) пользователя
     *
     * @return - респонс с данными пользователя
     */
    @GetMapping("user")
    @Operation(summary = "Текущий (авторизованный) пользователь")
    ResponseEntity<UserDto> getUser();
}
