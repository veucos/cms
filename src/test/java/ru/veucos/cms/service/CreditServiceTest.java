package ru.veucos.cms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.Bank;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.mapper.CreditMapper;
import ru.veucos.cms.mapper.CreditMapperImpl;
import ru.veucos.cms.mapper.UserMapper;
import ru.veucos.cms.mapper.UserMapperImpl;
import ru.veucos.cms.repository.CreditRepository;
import ru.veucos.cms.security.Role;
import ru.veucos.cms.service.impl.CreditServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CreditServiceTest {
    private final CreditMapper creditMapper = new CreditMapperImpl();
    private final UserMapper userMapper = new UserMapperImpl();
    List<Credit> creditList;
    List<User> userList;
    @InjectMocks
    private CreditServiceImpl service;
    @Mock
    private CreditMapper mapper;
    @Mock
    private CreditRepository repository;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userList = new ArrayList<>(Arrays.asList(
                new User(1L, "admin@test.ru", "Admin", "+7(123)4567890", "9999 123456", "", "", Role.ADMIN),
                new User(2L, "user@test.ru", "User", "+7(123)4567890", "9999 123456", "", "", Role.USER)
        ));
        Offer offer = new Offer(1L, "Offer1", new Bank(1L, "Bank1"), 1000000L, 60, 12);
        creditList = new ArrayList<>(Arrays.asList(
                new Credit(1L, userList.get(0), offer, 10000L, LocalDate.now()),
                new Credit(2L, userList.get(1), offer, 20000L, LocalDate.now())
        ));
    }

    @Test
    void getAll() {
        List<CreditDto> dtoList = creditList.stream().map(creditMapper::toDto).collect(Collectors.toList());
        UserDto userDto = userMapper.toDto(userList.get(0));
        when(repository.findAll()).thenReturn(creditList);
        when(userService.getCurrentUser()).thenReturn(userDto);
        when(userService.getModelById(userDto.getId())).thenReturn(userList.get(0));
        when(mapper.toDto(any(Credit.class))).thenReturn(dtoList.get(0), dtoList.get(1));
        List<CreditDto> creditList = service.getAll();
        assertThat(creditList).hasSize(2);
    }

    @Test
    void getModelById() {
        List<CreditDto> dtoList = creditList.stream().map(creditMapper::toDto).collect(Collectors.toList());
        UserDto userDto = userMapper.toDto(userList.get(0));
        when(repository.findById(anyLong())).thenReturn(Optional.of(creditList.get(0)));
        when(userService.getCurrentUser()).thenReturn(userDto);
        when(userService.getModelById(userDto.getId())).thenReturn(userList.get(0));
        when(mapper.toDto(any(Credit.class))).thenReturn(dtoList.get(0), dtoList.get(1));
        Credit credit = service.getModelById(creditList.get(0).getId());
        assertThat(credit.getId()).isEqualTo(creditList.get(0).getId());
    }

    @Test
    void deleteById() {
        List<CreditDto> dtoList = creditList.stream().map(creditMapper::toDto).collect(Collectors.toList());
        UserDto userDto = userMapper.toDto(userList.get(0));
        when(repository.findById(anyLong())).thenReturn(Optional.of(creditList.get(0)));
        when(userService.getCurrentUser()).thenReturn(userDto);
        doNothing().when(repository).delete(creditList.get(0));

        service.setRepository(repository);
        service.setMapper(mapper);
//        service.delete(1L);
//        verify(repository, times(1)).delete(creditList.get(0));
    }

    @Test
    void getSchedule() {
    }

    @Test
    void create() {
    }


}