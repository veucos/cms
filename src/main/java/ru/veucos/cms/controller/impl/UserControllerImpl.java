package ru.veucos.cms.controller.impl;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;

/**
 * Контроллер справочника пользователей(клиентов)
 * Основан на общем контроллере BaseController
 */
@RestController
@RequestMapping(value = "api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Клиенты")
@SecurityRequirement(name = "authorization")
@Log4j2
public class UserControllerImpl extends BaseControllerImpl<User, UserDto, Long> {
    @Hidden
    @Override
    public ResponseEntity<UserDto> create(UserDto createDto) {
        log.warn("Вызов запрещенного скрытого метода: UserControllerImpl->create: " + createDto.toString());
        return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
