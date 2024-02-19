package ru.veucos.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Данные графика платежей
 */
@Data
@AllArgsConstructor
public class ScheduleDto {
    private LocalDate date;
    private Double monthly;
    private Double body;
    private Double percent;
    private Double remainBody;
}
