package ru.veucos.cms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.veucos.cms.controller.impl.UserControllerImpl;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.security.Role;
import ru.veucos.cms.security.WithMockAdminUser;
import ru.veucos.cms.service.BaseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({UserControllerImpl.class})
@WithMockAdminUser
class UserControllerAdminUserTest {
    List<UserDto> usersDto;
    @InjectMocks
    ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Qualifier("userService")
    private BaseService service;

    @BeforeEach
    void setUp() {
        usersDto = new ArrayList<>(Arrays.asList(
                new UserDto(1L, "User1@test.ru", "User1", "+7(123)4567890", "9999 123456", "", Role.USER, ""),
                new UserDto(2L, "User2@test.ru", "User2", "+7(123)4567890", "9999 123456", "", Role.USER, "")
        ));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check receiving all users")
    void getAllUsers() throws Exception {
        when(service.getAll()).thenReturn(usersDto);

        mockMvc.perform(get("/api/users/all"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    @DisplayName("check receiving single user details")
    void getUserById() throws Exception {
        when(service.getById(anyLong())).thenReturn(usersDto.get(0));

        mockMvc.perform(get("/api/users?key=1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", Matchers.hasSize(7)))
                .andExpect(jsonPath("$.name").value("User1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("check updating a user")
    void updateUser() throws Exception {
        when(service.update(anyLong(), any(UserDto.class))).thenReturn(usersDto.get(0));

        mockMvc.perform(put("/api/users?key=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(usersDto.get(0)))
                )
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().is(403));
    }

    @Test
    @DisplayName("check deleting a user by id")
    void deleteUserById() throws Exception {
        when(service.delete(anyLong())).thenReturn(usersDto.get(0));

        mockMvc.perform(delete("/api/users/1"))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().is(403));
    }
}