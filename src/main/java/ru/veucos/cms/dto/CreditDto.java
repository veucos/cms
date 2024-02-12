package ru.veucos.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.entity.User;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class CreditDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userName;
    private OfferDto offer;
    private Long amount;
    private Date date;
}
