package ru.veucos.cms.service;

import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.dto.CreditInfoDto;
import ru.veucos.cms.entity.Credit;

/**
 * Сервис кредита (интерфейс)
 */
public interface CreditService extends BaseService<Credit, CreditDto, Long> {
    /**
     * Определение графика платежей
     *
     * @param creditId
     * @return
     */
    CreditInfoDto getSchedule(Long creditId);
}
