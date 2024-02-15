package ru.veucos.cms.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.entity.Offer;

@RestController
@RequestMapping(value = "api/credits", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Credit", description = "Кредиты")
@SecurityRequirement(name = "authorization")
public class CreditControllerImpl extends BaseControllerImpl<Credit, CreditDto, Long> {

}
