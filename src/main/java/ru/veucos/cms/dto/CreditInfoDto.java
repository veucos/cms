package ru.veucos.cms.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Информация по кредиту
 */
@Data
public class CreditInfoDto {
    Double fullAmount;
    Double percentAmount;
    Double realRate;
    LocalDate lastDate;
    List<ScheduleDto> schedule;
}
