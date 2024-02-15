package ru.veucos.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.dto.AuthUserDto;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.service.authentication.AuthenticationService;
import ru.veucos.cms.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Авторизация")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserServiceImpl userService;

    @PostMapping("signup")
    @Operation(summary = "Регистрация")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto user) {
        if (userService.isUserExist(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @PostMapping("signin")
    @Operation(summary = "Авторизация")
    public ResponseEntity<UserDto> signIn(@RequestBody AuthUserDto user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJwt(user), HttpStatus.OK);
    }
}
