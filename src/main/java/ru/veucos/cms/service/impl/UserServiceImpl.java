package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.repository.UserRepository;
import ru.veucos.cms.security.Role;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User, UserDto, Long> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getModelByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
    public UserDto getByEmail(String email) {
        return mapper.toDto(userRepository.findByEmail(email).get());
    }

    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDto create(UserDto dto) {
        User user = mapper.fromDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return mapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto update(Long key, UserDto dto) {
        User user = repository.getById(key);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        mapper.update(dto, user);
        return mapper.toDto(repository.save(user));
    }
}
