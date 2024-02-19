package ru.veucos.cms.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.mapper.UserMapper;
import ru.veucos.cms.mapper.UserMapperImpl;
import ru.veucos.cms.repository.UserRepository;
import ru.veucos.cms.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private final UserMapper userMapper = new UserMapperImpl();
    List<User> users;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserMapper mapper;
    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        users = Stream.of(mock(User.class), mock(User.class)).collect(Collectors.toList());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check finding all users")
    void findAll() {
        when(repository.findAll()).thenReturn(users);
        List<UserDto> userList = service.getAll();
        assertThat(userList).hasSize(2);
    }

    @Test
    @DisplayName("check finding user by Id")
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(users.get(0)));
        User user = service.getModelById(1L);
        assertThat(user).isNotNull().isEqualTo(users.get(0));
    }

    @Test
    @DisplayName("check create user")
    void save() {
        User user = users.get(0);
        UserDto userDto = userMapper.toDto(user);
        when(mapper.fromDto(userDto)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(userDto);
        userDto = service.create(userDto);
        assertThat(userDto.getName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("check update user")
    void update() {
        User user = users.get(0);
        UserDto userDto = userMapper.toDto(user);
        when(repository.getById(anyLong())).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(userDto);
        userDto = service.update(user.getId(), userDto);
        assertThat(userDto.getName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("check delete user by Id")
    void deleteById() {
        when(repository.getById(anyLong())).thenReturn(users.get(0));
        doNothing().when(repository).delete(users.get(0));

        service.delete(1L);
        verify(repository, times(1)).delete(users.get(0));

    }

}