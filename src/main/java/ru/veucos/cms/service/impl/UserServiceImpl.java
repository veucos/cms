package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User, UserDto, Long> {
    private final UserRepository userRepository;

    public User getModelByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
