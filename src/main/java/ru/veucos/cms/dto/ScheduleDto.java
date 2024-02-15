package ru.veucos.cms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDto {
    private Date date;
    private Double total;
    private Double body;
    private Double percent;
}
