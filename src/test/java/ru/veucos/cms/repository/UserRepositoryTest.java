package ru.veucos.cms.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.security.Role;

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
        assertThat(users.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("check finding user by Id")
    void findById() {
        Optional<User> user = repository.findById(100001L);
        assertThat(user.get().getName()).isEqualTo("User1");
    }

    @Test
    @DisplayName("check delete user by Id")
    void deleteById() {
        repository.deleteById(100001L);
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("check create user")
    void create() {
        repository.save(new User(100004L, "user4@test.ru", "User4", "+7(123)456-7890", "9988 123456", "$2a$10$c6vaCuFKArxAHhlpCknkvOwqvVpI5LFrxvr34z3YmGi7Ups0b6VsS", "", Role.USER));
        Optional<User> user = repository.findById(100004L);
        assertThat(user.get().getName()).isEqualTo("User4");
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("check update user")
    void update() {
        repository.save(new User(100003L, "user4@test.ru", "User4", "+7(123)456-7890", "9988 123456", "$2a$10$c6vaCuFKArxAHhlpCknkvOwqvVpI5LFrxvr34z3YmGi7Ups0b6VsS", "", Role.USER));
        Optional<User> user = repository.findById(100003L);
        assertThat(user.get().getName()).isEqualTo("User4");
        List<User> users = repository.findAll();
        assertThat(users.size()).isEqualTo(5);
    }
}