package ru.veucos.cms.controller.impl;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;

// http://localhost:8080/swagger-ui/index.html#/
@RestController
@RequestMapping(value = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Клиенты")
@SecurityRequirement(name = "authorization")
public class UserControllerImpl extends BaseControllerImpl<User, UserDto, Long> {

    @Hidden
    @Override
    public ResponseEntity<UserDto> create(UserDto createDto) {
        return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
