package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.exception.NoAuthenticatedException;
import ru.veucos.cms.exception.NotFoundException;
import ru.veucos.cms.repository.UserRepository;
import ru.veucos.cms.security.AuthenticationFacadeImpl;
import ru.veucos.cms.security.Role;
import ru.veucos.cms.service.UserService;

/**
 * Сервис пользователей (клиентов)
 */
@Service("userService")
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl extends BaseServiceImpl<User, UserDto, Long> implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacadeImpl authenticationFacade;
    private UserRepository userRepository;

    @Autowired
    protected void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Выборка entity по почте
     *
     * @param email
     * @return
     */
    @Override
    public User getModelByEmail(String email) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return userRepository.findByEmail(email).get();
    }

    /**
     * Выборка DTO по почте
     *
     * @param email
     * @return
     */
    @Override
    public UserDto getByEmail(String email) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return mapper.toDto(userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Пользователь по email " + email + " не найден")));
    }

    /**
     * Проверка пользователя на существование по ключу
     *
     * @param email
     * @return
     */
    @Override
    public boolean isUserExist(String email) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Создание пользователя
     *
     * @param dto
     * @return
     */
    @Override
    public UserDto create(UserDto dto) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        User user = mapper.fromDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return mapper.toDto(userRepository.save(user));
    }

    /**
     * Обновление пользователя
     *
     * @param key
     * @param dto
     * @return
     */
    @Override
    public UserDto update(Long key, UserDto dto) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        User user = repository.getById(key);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        mapper.update(dto, user);
        return mapper.toDto(repository.save(user));
    }

    /**
     * Определение текущего пользователя
     *
     * @return
     */
    @Override
    public UserDto getCurrentUser() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        if (!authenticationFacade.getAuthentication().isAuthenticated()) {
            log.warn("Попытка работы без авторизации");
            throw new NoAuthenticatedException("Пользователь не авторизован");
        }
        if (authenticationFacade.getAuthentication().getName().equalsIgnoreCase("anonymousUser")) {
            log.warn("Попытка работы без авторизации");
            throw new NoAuthenticatedException("Пользователь не авторизован");
        }
        return getByEmail(authenticationFacade.getAuthentication().getName());
    }
}
