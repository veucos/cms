package ru.veucos.cms.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.dto.BankDto;
import ru.veucos.cms.entity.Bank;

@RestController
@RequestMapping(value = "api/banks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Banks", description = "Банки")
@SecurityRequirement(name = "authorization")
public class BankControllerImpl extends BaseControllerImpl<Bank, BankDto, Long> {

}
