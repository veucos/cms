package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.dto.ScheduleDto;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.exception.AppErrorException;
import ru.veucos.cms.exception.NoAuthenticatedException;
import ru.veucos.cms.exception.NotFoundException;
import ru.veucos.cms.repository.CreditRepository;
import ru.veucos.cms.repository.OfferRepository;
import ru.veucos.cms.security.Role;
import ru.veucos.cms.service.CreditService;
import ru.veucos.cms.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис кредитов
 */
@RequiredArgsConstructor
@Service("creditService")
@Log4j2
public class CreditServiceImpl extends BaseServiceImpl<Credit, CreditDto, Long> implements CreditService {
    private final UserService userService;
    private final OfferRepository offerRepository;
    private final CreditRepository creditRepository;

    /**
     * Проверка что идет обработка данных текущего пользователя
     *
     * @param creditId
     */
    private void checkCurrentUser(Long creditId) {
        UserDto userDto = userService.getCurrentUser();
        Credit credit = getModelById(creditId);
        if (!credit.getUser().getId().equals(userDto.getId())) {
            log.warn("Попытка обработки чужого кредита пользователем " + userDto.getEmail());
            throw new NoAuthenticatedException("Обработка чужого кредита запрещена");
        }
    }

    /**
     * Удаление по ключу
     *
     * @param key
     * @return
     */
    @Override
    public CreditDto delete(Long key) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        checkCurrentUser(key);
        return super.delete(key);
    }

    /**
     * Определение графика платежей
     *
     * @param creditId
     * @return
     */
    @Override
    public List<ScheduleDto> getSchedule(Long creditId) {
        Double body, percent, remainBody;
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        checkCurrentUser(creditId);
        Credit credit = getModelById(creditId);
        Double monthRate = credit.getOffer().getRate() / 12.0 / 100;
        Double monthPayment = Math.round(100 * credit.getAmount() * (monthRate / (1 - Math.pow(1 + monthRate, -credit.getOffer().getTerm())))) / 100.0;
        Double sum = 0.0;
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (int i = 1; i < credit.getOffer().getTerm(); i++) {
            body = Math.round(100 * monthPayment / Math.pow(1 + monthRate, credit.getOffer().getTerm() - i + 1)) / 100.0;
            percent = Math.round(100 * (monthPayment - body)) / 100.0;
            sum += body;
            remainBody = Math.round(100 * credit.getAmount() - sum) / 100.0;
            scheduleDtos.add(new ScheduleDto(credit.getDate().plusMonths(i - 1),
                    body + percent,
                    body,
                    percent,
                    remainBody));

        }
        body = Math.round(100 * credit.getAmount() - sum) / 100.0;
        percent = Math.round(100 * monthPayment - body) / 100.0;
        remainBody = Math.round(100 * credit.getAmount() - sum - body) / 100.0;
        scheduleDtos.add(new ScheduleDto(credit.getDate().plusMonths(credit.getOffer().getTerm() - 1),
                body + percent,
                body,
                percent,
                remainBody));
        return scheduleDtos;
    }

    /**
     * Создание кредита
     *
     * @param dto
     * @return
     */
    @Override
    public CreditDto create(CreditDto dto) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        dto.setDate(LocalDate.now());
        UserDto userDto = userService.getCurrentUser();
        dto.setUserId(userDto.getId());
        dto.setUserName(userDto.getName());
        Offer offer = offerRepository.getById(dto.getOfferId());
        if (offer.getId() == null) {
            throw new AppErrorException("Предложение по ключу " + dto.getOfferId() + " не найдено");
        }
        if (offer.getLim() < dto.getAmount()) {
            throw new AppErrorException("Сумма по кредиту " + dto.getAmount() + " превышает лимит " + offer.getLim());
        }
        Credit newCredit = repository.save(mapper.fromDto(dto));
        newCredit.setOffer(offer);

        return getById(newCredit.getId());
    }

    /**
     * Выборка всех данных
     * Если пользователь не ADMIN, то только данных текущего пользователя
     *
     * @return
     */
    @Override
    public List<CreditDto> getAll() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        UserDto userDto = userService.getCurrentUser();
        User user = userService.getModelById(userDto.getId());
        return userDto.getRole().equals(Role.ADMIN)
                ? creditRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList())
                : creditRepository.getByUser(user).stream().map(mapper::toDto).collect(Collectors.toList());

    }

    /**
     * Выборка всех данных (пагинация)
     * Если пользователь не ADMIN, то только данных текущего пользователя
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<CreditDto> getPage(Integer pageNum, Integer pageSize) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        UserDto userDto = userService.getCurrentUser();
        User user = userService.getModelById(userDto.getId());
        return userDto.getRole().equals(Role.ADMIN)
                ? creditRepository.findAll(PageRequest.of(pageNum, pageSize)).stream().map(mapper::toDto).collect(Collectors.toList())
                : creditRepository.getByUser(user, PageRequest.of(pageNum, pageSize)).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    /**
     * Выборка entity по ключу
     *
     * @param key
     * @return
     */
    @Override
    public Credit getModelById(Long key) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        UserDto userDto = userService.getCurrentUser();
        Optional<Credit> creditOptional = repository.findById(key);
        if (!creditOptional.isPresent()) {
            throw new NotFoundException("Данные по ключу " + key + " не найдены");
        }
        if (!creditOptional.get().getUser().getId().equals(userDto.getId())) {
            log.warn("Попытка обработки чужого кредита пользователем " + userDto.getEmail());
            throw new NoAuthenticatedException("Обработка чужого кредита запрещена");
        }
        return creditOptional.orElseThrow(() -> new NotFoundException("Данные по ключу " + key + " не найдены"));

    }
}
