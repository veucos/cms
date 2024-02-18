package ru.veucos.cms.service.authentication;

import ru.veucos.cms.dto.AuthUserDto;
import ru.veucos.cms.dto.UserDto;

/**
 * Сервис аутентификации (интерфейс)
 */
public interface AuthenticationService {
    /**
     * Аутентификация и генерация JWT токена
     *
     * @param signInRequest параметры пользователя и пароль
     * @return
     */
    UserDto signInAndReturnJwt(AuthUserDto signInRequest);
}
