package ru.veucos.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Данные справочника предложений по кредиту
 */
@Data
public class OfferDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @NotBlank(message = "Название предложения обязательно")
    private String name;
    @NotBlank(message = "Банк Id> обязателен")
    private Long bankId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String bankName;
    @NotBlank(message = "Максимальная сумма кредита обязательна")
    private Long lim;
    @NotBlank(message = "Продолжнительность кредита обязательна")
    private Integer term;
    @NotBlank(message = "Процентная ставка кредита обязательна")
    private Integer rate;
}
