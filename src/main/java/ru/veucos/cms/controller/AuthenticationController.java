package ru.veucos.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.veucos.cms.dto.AuthUserDto;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.security.AuthenticationFacadeImpl;
import ru.veucos.cms.service.authentication.AuthenticationService;
import ru.veucos.cms.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Авторизация")
@SecurityRequirement(name = "authorization")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserServiceImpl userService;
    private final AuthenticationFacadeImpl authenticationFacade;

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

    @GetMapping("user")
    public ResponseEntity<UserDto> getUser() {
        return new ResponseEntity<>(userService.getByEmail(authenticationFacade.getAuthentication().getName()), HttpStatus.OK);
    }
}
