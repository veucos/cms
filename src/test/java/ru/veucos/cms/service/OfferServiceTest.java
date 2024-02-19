package ru.veucos.cms.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.mapper.OfferMapper;
import ru.veucos.cms.mapper.OfferMapperImpl;
import ru.veucos.cms.repository.OfferRepository;
import ru.veucos.cms.service.impl.OfferServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class OfferServiceTest {
    private final OfferMapper offerMapper = new OfferMapperImpl();
    List<Offer> offers;
    @InjectMocks
    private OfferServiceImpl service;
    @Mock
    private OfferMapper mapper;
    @Mock
    private OfferRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        offers = Stream.of(mock(Offer.class), mock(Offer.class)).collect(Collectors.toList());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check finding all offers")
    void findAll() {
        when(repository.findAll()).thenReturn(offers);
        List<OfferDto> offerList = service.getAll();
        assertThat(offerList).hasSize(2);
    }

    @Test
    @DisplayName("check finding offer by Id")
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(offers.get(0)));
        Offer offer = service.getModelById(1L);
        assertThat(offer).isNotNull().isEqualTo(offers.get(0));
    }

    @Test
    @DisplayName("check create offer")
    void save() {
        Offer offer = offers.get(0);
        OfferDto offerDto = offerMapper.toDto(offer);
        when(mapper.fromDto(offerDto)).thenReturn(offer);
        when(repository.save(offer)).thenReturn(offer);
        when(mapper.toDto(offer)).thenReturn(offerDto);
        offerDto = service.create(offerDto);
        assertThat(offerDto.getName()).isEqualTo(offer.getName());
    }

    @Test
    @DisplayName("check update offer")
    void update() {
        Offer offer = offers.get(0);
        OfferDto offerDto = offerMapper.toDto(offer);
        when(repository.getById(anyLong())).thenReturn(offer);
        when(repository.save(offer)).thenReturn(offer);
        when(mapper.toDto(offer)).thenReturn(offerDto);
        offerDto = service.update(offer.getId(), offerDto);
        assertThat(offerDto.getName()).isEqualTo(offer.getName());
    }

    @Test
    @DisplayName("check delete offer by Id")
    void deleteById() {
        when(repository.getById(anyLong())).thenReturn(offers.get(0));
        doNothing().when(repository).delete(offers.get(0));

        service.delete(1L);
        verify(repository, times(1)).delete(offers.get(0));

    }

}