package ru.veucos.cms.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.veucos.cms.entity.Bank;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class BankRepositoryTest {
    @Autowired
    private BankRepository repository;

    @Test
    @DisplayName("check finding all banks")
    void findAll() {
        List<Bank> banks = repository.findAll();
        assertThat(banks).hasSize(6);
    }

    @Test
    @DisplayName("check finding bank by Id")
    void findById() {
        Optional<Bank> bank = repository.findById(1001L);
        assertThat(bank.get().getName()).isEqualTo("Bank1");
    }

    @Test
    @DisplayName("check delete bank by Id")
    void deleteById() {
        repository.deleteById(1003L);
        List<Bank> banks = repository.findAll();
        assertThat(banks).hasSize(5);
    }

    @Test
    @DisplayName("check create bank")
    void create() {
        repository.save(new Bank(1004L, "Bank4"));
        Optional<Bank> bank = repository.findById(1004L);
        assertThat(bank.get().getName()).isEqualTo("Bank4");
        List<Bank> banks = repository.findAll();
        assertThat(banks).hasSize(7);
    }

    @Test
    @DisplayName("check update bank")
    void update() {
        repository.save(new Bank(1003L, "Bank4"));
        Optional<Bank> bank = repository.findById(1003L);
        assertThat(bank.get().getName()).isEqualTo("Bank4");
        List<Bank> banks = repository.findAll();
        assertThat(banks).hasSize(6);
    }
}