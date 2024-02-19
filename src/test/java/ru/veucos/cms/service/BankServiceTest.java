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
import ru.veucos.cms.dto.BankDto;
import ru.veucos.cms.entity.Bank;
import ru.veucos.cms.mapper.BankMapper;
import ru.veucos.cms.mapper.BankMapperImpl;
import ru.veucos.cms.repository.BankRepository;
import ru.veucos.cms.service.impl.BankServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {
    private final BankMapper bankMapper = new BankMapperImpl();
    List<Bank> banks;
    @InjectMocks
    private BankServiceImpl service;
    @Mock
    private BankMapper mapper;
    @Mock
    private BankRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        banks = Stream.of(mock(Bank.class), mock(Bank.class)).collect(Collectors.toList());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check finding all banks")
    void findAll() {
        when(repository.findAll()).thenReturn(banks);
        List<BankDto> bankList = service.getAll();
        assertThat(bankList).hasSize(2);
    }

    @Test
    @DisplayName("check finding bank by Id")
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(banks.get(0)));
        Bank bank = service.getModelById(1L);
        assertThat(bank).isNotNull().isEqualTo(banks.get(0));
    }

    @Test
    @DisplayName("check create bank")
    void save() {
        Bank bank = banks.get(0);
        BankDto bankDto = bankMapper.toDto(bank);
        when(mapper.fromDto(bankDto)).thenReturn(bank);
        when(repository.save(bank)).thenReturn(bank);
        when(mapper.toDto(bank)).thenReturn(bankDto);
        bankDto = service.create(bankDto);
        assertThat(bankDto.getName()).isEqualTo(bank.getName());
    }

    @Test
    @DisplayName("check update bank")
    void update() {
        Bank bank = banks.get(0);
        BankDto bankDto = bankMapper.toDto(bank);
        when(repository.getById(anyLong())).thenReturn(bank);
        when(repository.save(bank)).thenReturn(bank);
        when(mapper.toDto(bank)).thenReturn(bankDto);
        bankDto = service.update(bank.getId(), bankDto);
        assertThat(bankDto.getName()).isEqualTo(bank.getName());
    }

    @Test
    @DisplayName("check delete bank by Id")
    void deleteById() {
        when(repository.getById(anyLong())).thenReturn(banks.get(0));
        doNothing().when(repository).delete(banks.get(0));

        service.delete(1L);
        verify(repository, times(1)).delete(banks.get(0));

    }

}