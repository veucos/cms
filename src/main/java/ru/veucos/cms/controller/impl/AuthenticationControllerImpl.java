package ru.veucos.cms.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.controller.AuthenticationController;
import ru.veucos.cms.dto.AuthUserDto;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.service.UserService;
import ru.veucos.cms.service.authentication.AuthenticationService;

import javax.validation.Valid;

/**
 * Контроллер авторизации
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Авторизация")
@SecurityRequirement(name = "authorization")
@Log4j2
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    /**
     * Запрос регистрации
     *
     * @param user данные пользователя
     * @return - респонс с данными пользователя
     */
    @Override
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid UserDto user) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        if (userService.isUserExist(user.getEmail())) {
            log.warn("Пользователь уже существует: " + user.getEmail());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    /**
     * Запрос авторизации
     *
     * @param user - пользователь, пароль
     * @return - респонс с данными пользователя
     */
    @Override
    public ResponseEntity<UserDto> signIn(@RequestBody @Valid AuthUserDto user) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(authenticationService.signInAndReturnJwt(user), HttpStatus.OK);
    }

    /**
     * Запрос получения текущего(авторизованного) пользователя
     *
     * @return - респонс с данными пользователя
     */
    @Override
    public ResponseEntity<UserDto> getUser() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }
}
