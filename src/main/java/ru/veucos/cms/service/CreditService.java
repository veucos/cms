package ru.veucos.cms.service;

import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.dto.ScheduleDto;
import ru.veucos.cms.entity.Credit;

import java.util.List;

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
    List<ScheduleDto> getSchedule(Long creditId);
}
