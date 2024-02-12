package ru.veucos.cms.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.veucos.cms.entity.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    @DisplayName("check finding all users")
    void findAll() {
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("check finding user by Id")
    void findById() {
        Optional<User> user = repository.findById(1L);
        assertThat(user.get().getName()).isEqualTo("User1");
    }

    @Test
    @DisplayName("check delete user by Id")
    void deleteById() {
        repository.deleteById(1L);
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("check create user")
    void create() {
        repository.save(new User(4L, "User4", 44, "123456"));
        Optional<User> user = repository.findById(4L);
        assertThat(user.get().getName()).isEqualTo("User4");
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("check update user")
    void update() {
        repository.save(new User(3L, "User4", 44, "123456"));
        Optional<User> user = repository.findById(3L);
        assertThat(user.get().getName()).isEqualTo("User4");
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(3);
    }
}