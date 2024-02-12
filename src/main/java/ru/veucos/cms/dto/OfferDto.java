package ru.veucos.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OfferDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    private String name;
    private Long bankId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long bankName;
    private Long lim;
    private Integer term;
    private Integer rate;
}
