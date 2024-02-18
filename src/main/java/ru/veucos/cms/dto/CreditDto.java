package ru.veucos.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Данные кредита клиента
 */
@Data
public class CreditDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Id предложения обязательно")
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
    @NotBlank(message = "Сумма кредита обязательна")
    private Long amount;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate date;
}
