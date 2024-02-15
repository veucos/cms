package ru.veucos.cms.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.dto.BankDto;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Bank;
import ru.veucos.cms.entity.Offer;

@RestController
@RequestMapping(value = "api/offers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Offer", description = "Предложения")
@SecurityRequirement(name = "authorization")
public class OfferControllerImpl extends BaseControllerImpl<Offer, OfferDto, Long> {

}
