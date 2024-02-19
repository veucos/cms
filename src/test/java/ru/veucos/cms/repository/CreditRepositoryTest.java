package ru.veucos.cms.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.veucos.cms.entity.Bank;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.security.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class CreditRepositoryTest {
    @Autowired
    private CreditRepository repository;

    @Test
    @DisplayName("check finding all credits")
    void findAll() {
        List<Credit> credits = repository.findAll();
        assertThat(credits).hasSize(3);
    }

    @Test
    @DisplayName("check finding credit by Id")
    void findById() {
        Optional<Credit> credit = repository.findById(1001L);
        assertThat(credit.get().getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("check delete credit by Id")
    void deleteById() {
        repository.deleteById(1003L);
        List<Credit> credits = repository.findAll();
        assertThat(credits).hasSize(2);
    }

    @Test
    @DisplayName("check create credit")
    void create() {
        User user = new User(1001L, "user1@test.ru", "User1", "+7(123)456-7890", "9988 123456", "$2a$10$c6vaCuFKArxAHhlpCknkvOwqvVpI5LFrxvr34z3YmGi7Ups0b6VsS", "", Role.USER);
        Offer offer = new Offer(1001L, "Offer1", new Bank(1001L, "Банк 1"), 1000000L, 60, 12);
        repository.save(new Credit(1004L, user, offer, 40000L, LocalDate.now()));
        Optional<Credit> credit = repository.findById(1004L);
        assertThat(credit.get().getAmount()).isEqualTo(40000);
        List<Credit> credits = repository.findAll();
        assertThat(credits).hasSize(4);
    }

    @Test
    @DisplayName("check update credit")
    void update() {
        User user = new User(1001L, "user1@test.ru", "User1", "+7(123)456-7890", "9988 123456", "$2a$10$c6vaCuFKArxAHhlpCknkvOwqvVpI5LFrxvr34z3YmGi7Ups0b6VsS", "", Role.USER);
        Offer offer = new Offer(1001L, "Offer1", new Bank(1001L, "Банк 1"), 1000000L, 60, 12);
        repository.save(new Credit(1003L, user, offer, 40000L, LocalDate.now()));
        Optional<Credit> credit = repository.findById(1003L);
        assertThat(credit.get().getAmount()).isEqualTo(40000);
        List<Credit> credits = repository.findAll();
        assertThat(credits).hasSize(3);
    }

}