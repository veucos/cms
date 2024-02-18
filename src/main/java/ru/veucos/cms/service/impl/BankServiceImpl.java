package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.BankDto;
import ru.veucos.cms.entity.Bank;

/**
 * Сервис справочника банков
 */
@RequiredArgsConstructor
@Service("bankService")
public class BankServiceImpl extends BaseServiceImpl<Bank, BankDto, Long> {

}
