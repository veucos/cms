package ru.veucos.cms.controller.impl;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.dto.ScheduleDto;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.service.CreditService;

import java.util.List;

/**
 * Контроллер кредитов
 * Основан на общем контроллере BaseController
 */
@RestController
@RequestMapping(value = "api/credits", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Credit", description = "Кредиты")
@SecurityRequirement(name = "authorization")
@Log4j2
public class CreditControllerImpl extends BaseControllerImpl<Credit, CreditDto, Long> {
    @Autowired
    private CreditService creditService;

    @Operation(summary = "График платежей")
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleDto>> getSchedule(Long creditId) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(creditService.getSchedule(creditId), HttpStatus.OK);
    }

    @Hidden
    @Override
    public ResponseEntity<CreditDto> update(Long key, CreditDto dto) {
        log.warn("Вызов запрещенного скрытого метода: CreditControllerImpl->update: " + dto.toString());
        return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Hidden
    @Override
    public ResponseEntity<Void> deleteAll() {
        log.warn("Вызов запрещенного скрытого метода: CreditControllerImpl->deleteAll");
        return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
