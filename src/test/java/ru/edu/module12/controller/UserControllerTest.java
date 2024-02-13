package ru.veucos.cms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.veucos.cms.controller.impl.UserControllerImpl;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.service.impl.UserServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({UserControllerImpl.class})
class UserControllerTest {
    List<User> users;
    @InjectMocks
    ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
//        users = new ArrayList<>(Arrays.asList(
//                new User(1L, "User1", 11, "123456"),
//                new User(2L, "User2", 22, "123456")
//        ));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check receiving all users")
    void getAllUsers() throws Exception {

        when(service.findAll()).thenReturn(users);

        mockMvc.perform(get("api/v1/users"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    @DisplayName("check receiving single user details")
    void getUserById() throws Exception {
        when(service.findById(anyLong())).thenReturn(users.get(0));

        mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.name").value("User1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("check adding a new user")
    void createUser() throws Exception {
        when(service.save(any(User.class))).thenReturn(users.get(1));

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(users.get(1)))
                )
                .andDo(print())
                .andExpect(header().string("Location", "/api/v1/users/" + users.get(1).getId()))
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("check updating a user")
    void updateUser() throws Exception {
        when(service.update(any(User.class))).thenReturn(users.get(0));

        mockMvc.perform(put("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(users.get(0)))
                )
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("check deleting a user by id")
    void deleteUserById() throws Exception {
        doNothing().when(service).deleteById(anyLong());

        mockMvc.perform(delete("/api/v1/users/1"))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());
    }
}