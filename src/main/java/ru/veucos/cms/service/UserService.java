package ru.veucos.cms.service;

import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;

/**
 * Сервис пользователей (клиентов) интерфейс
 */
public interface UserService extends BaseService<User, UserDto, Long> {
    /**
     * Выборка entity по почте
     *
     * @param email
     * @return
     */
    User getModelByEmail(String email);

    /**
     * Выборка DTO по почте
     *
     * @param email
     * @return
     */
    UserDto getByEmail(String email);

    /**
     * Проверка пользователя на существование по ключу
     *
     * @param email
     * @return
     */
    boolean isUserExist(String email);

    /**
     * Определение текущего пользователя
     *
     * @return
     */
    UserDto getCurrentUser();
}
