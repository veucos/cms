package ru.veucos.cms.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.veucos.cms.entity.Bank;
import ru.veucos.cms.entity.Offer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class OfferRepositoryTest {
    @Autowired
    private OfferRepository repository;

    @Test
    @DisplayName("check finding all offers")
    void findAll() {
        List<Offer> offers = repository.findAll();
        assertThat(offers).hasSize(5);
    }

    @Test
    @DisplayName("check finding offer by Id")
    void findById() {
        Optional<Offer> offer = repository.findById(1001L);
        assertThat(offer.get().getName()).isEqualTo("Offer1");
    }

    @Test
    @DisplayName("check delete offer by Id")
    void deleteById() {
        repository.deleteById(1003L);
        List<Offer> offers = repository.findAll();
        assertThat(offers).hasSize(4);
    }

    @Test
    @DisplayName("check create offer")
    void create() {
        repository.save(new Offer(1004L, "Offer4", new Bank(1001L, "Банк 1"), 1000000L, 60, 12));
        Optional<Offer> offer = repository.findById(1004L);
        assertThat(offer.get().getName()).isEqualTo("Offer4");
        List<Offer> offers = repository.findAll();
        assertThat(offers).hasSize(6);
    }

    @Test
    @DisplayName("check update offer")
    void update() {
        repository.save(new Offer(1003L, "Offer4", new Bank(1001L, "Банк 1"), 1000000L, 60, 12));
        Optional<Offer> offer = repository.findById(1003L);
        assertThat(offer.get().getName()).isEqualTo("Offer4");
        List<Offer> offers = repository.findAll();
        assertThat(offers).hasSize(5);
    }
}