package ru.veucos.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class CreditDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long offerId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String offerName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long bankId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String bankName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long lim;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer term;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer rate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long amount;
    private Date date;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ArrayList<ScheduleDto> schedule;
}
